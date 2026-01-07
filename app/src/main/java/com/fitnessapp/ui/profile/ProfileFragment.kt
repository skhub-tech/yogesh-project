package com.fitnessapp.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fitnessapp.R

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvUserInfo = view.findViewById<TextView>(R.id.tvUserInfo)

        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                val info = """
                    Name: ${user.name}
                    Email: ${user.email}
                    Age: ${user.age}
                    Gender: ${user.gender}
                    Height: ${user.heightCm} cm
                    Weight: ${user.weightKg} kg
                    Goal: ${user.fitnessGoal.replace("_", " ").capitalize()}
                """.trimIndent()
                tvUserInfo.text = info
            } else {
                tvUserInfo.text = "Loading Profile..."
            }
        }
    }
}
