package com.fitnessapp.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fitnessapp.R

import androidx.navigation.fragment.findNavController

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
        val tvAge = view.findViewById<TextView>(R.id.tvAge)
        val tvGender = view.findViewById<TextView>(R.id.tvGender)
        val tvHeight = view.findViewById<TextView>(R.id.tvHeight)
        val tvWeight = view.findViewById<TextView>(R.id.tvWeight)
        val tvGoal = view.findViewById<TextView>(R.id.tvGoal)
        val ivProfileImage = view.findViewById<com.google.android.material.imageview.ShapeableImageView>(R.id.ivProfileImage)
        val btnEditProfile = view.findViewById<com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton>(R.id.btnEditProfile)

        // Load logged-in user's photo from Firebase Auth
        val currentUser = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
        if (currentUser?.photoUrl != null) {
             com.bumptech.glide.Glide.with(this)
                .load(currentUser.photoUrl)
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(ivProfileImage)
        }

        btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.setupProfileFragment)
        }

        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                tvName.text = user.name
                tvEmail.text = user.email
                tvAge.text = user.age.toString()
                tvGender.text = user.gender
                tvHeight.text = "${user.heightCm} cm"
                tvWeight.text = "${user.weightKg} kg"
                
                // Format goal string (e.g., "weight_loss" -> "Weight Loss")
                val formattedGoal = user.fitnessGoal
                    .lowercase()
                    .replace("_", " ")
                    .split(" ")
                    .joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
                
                tvGoal.text = formattedGoal
            } else {
                tvName.text = "Loading..."
            }
        }
    }
}
