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

    private lateinit var loginContent: View
    private lateinit var progressBar: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginContent = view.findViewById(R.id.loginContent)
        progressBar = view.findViewById(R.id.progressBar)
        
        // Initial State: Show Loading, Hide Content to prevent flicker
        showLoading(true)

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

        view.findViewById<View>(R.id.btn_google_login).setOnClickListener {
            Log.d("LoginFragment", "Login button clicked")
            signIn()
        }
        
        // IMPORTANT: Check session immediately
        val currentUser = auth.currentUser
        if (currentUser != null) {
            checkUserProfileAndNavigate()
        } else {
            // No user, show login content
            showLoading(false)
        }
    }

    private fun signIn() {
        showLoading(true)
        Log.d("LoginFragment", "Launching Sign-In Intent")
        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }
    
    // ... (Result Launcher remains same) ...

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        showLoading(true)
        Log.d("LoginFragment", "Authenticating with Firebase using Google credential")
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d("LoginFragment", "Firebase Authentication Successful")
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    checkUserProfileAndNavigate()
                } else {
                    showLoading(false) // Show Login UI again on failure
                    Log.e("LoginFragment", "Firebase Authentication Failed", task.exception)
                    Toast.makeText(context, "Authentication Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
    
    // Removed duplicate onStart check since we do it in onViewCreated to control UI state better
    
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            loginContent.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            loginContent.visibility = View.VISIBLE
        }
    }

    private fun checkUserProfileAndNavigate() {
        val user = auth.currentUser ?: return
        
        // Keep loading shown...
        
        val db = FirebaseFirestore.getInstance()
        
        db.collection("users").document(user.uid).get()
            .addOnSuccessListener { document ->
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
                showLoading(false) // Show login so they can try again or just retry?
                // Actually if profile check fails, we might still be logged in. 
                // But let's show login screen or error state. 
                // For now, revealing login screen allows them to maybe sign out/in again or just retry.
                Toast.makeText(context, "Failed to load profile. Please try again.", Toast.LENGTH_SHORT).show()
            }
    }
}
