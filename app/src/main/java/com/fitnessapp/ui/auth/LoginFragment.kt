package com.fitnessapp.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fitnessapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private val signInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d("LoginFragment", "Sign-in result received. ResultCode: ${result.resultCode}")
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            Log.d("LoginFragment", "Google Sign-In successful: ${account?.email}")
            account?.let { firebaseAuthWithGoogle(it) }
        } catch (e: ApiException) {
            Log.e("LoginFragment", "Google Sign-In failed code=${e.statusCode}", e)
            Toast.makeText(context, "Google Sign-In failed: ${e.message} (Code: ${e.statusCode})", Toast.LENGTH_LONG).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        Log.d("LoginFragment", "onViewCreated: Initializing Google Sign-In client")

        // Configure Google Sign In
        try {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("584591665738-kn96rtjdqgbu0fdi1g8p3hp6nnph1chd.apps.googleusercontent.com") 
                .requestEmail()
                .build()

            googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        } catch (e: Exception) {
            Log.e("LoginFragment", "Error configuring Google Sign-In", e)
        }

        view.findViewById<Button>(R.id.btn_google_login).setOnClickListener {
            Log.d("LoginFragment", "Login button clicked")
            signIn()
        }
    }

    private fun signIn() {
        Log.d("LoginFragment", "Launching Sign-In Intent")
        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("LoginFragment", "Authenticating with Firebase using Google credential")
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d("LoginFragment", "Firebase Authentication Successful")
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    checkUserProfileAndNavigate()
                } else {
                    Log.e("LoginFragment", "Firebase Authentication Failed", task.exception)
                    Toast.makeText(context, "Authentication Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
    
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            checkUserProfileAndNavigate()
        }
    }

    private fun checkUserProfileAndNavigate() {
        val user = auth.currentUser ?: return
        val db = FirebaseFirestore.getInstance()
        
        db.collection("users").document(user.uid).get()
            .addOnSuccessListener { document ->
                // Check if document exists and has basic profile data (e.g., age is set)
                // Note: User model default age is 0.
                if (document.exists() && document.contains("age") && (document.getLong("age") ?: 0) > 0) {
                    Log.d("LoginFragment", "User profile found, navigating to Home")
                    val navOptions = androidx.navigation.NavOptions.Builder()
                        .setPopUpTo(R.id.loginFragment, true)
                        .build()
                    findNavController().navigate(R.id.homeFragment, null, navOptions)
                } else {
                    Log.d("LoginFragment", "User profile missing or incomplete, navigating to Setup Profile")
                    val navOptions = androidx.navigation.NavOptions.Builder()
                        .setPopUpTo(R.id.loginFragment, true)
                        .build()
                    findNavController().navigate(R.id.setupProfileFragment, null, navOptions)
                }
            }
            .addOnFailureListener { e ->
                Log.e("LoginFragment", "Error checking user profile", e)
                // Fallback to Setup on error to ensure we don't block user, or stay on Login?
                // Better to go to Setup so they can try saving/overwriting.
                val navOptions = androidx.navigation.NavOptions.Builder()
                    .setPopUpTo(R.id.loginFragment, true)
                    .build()
                findNavController().navigate(R.id.setupProfileFragment, null, navOptions)
            }
    }
}
