package com.fitnessapp.ui.profile

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fitnessapp.R
import com.google.android.material.textfield.TextInputEditText

class SetupProfileFragment : Fragment(R.layout.fragment_setup_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var etAge: TextInputEditText
    private lateinit var rgGender: RadioGroup
    private lateinit var etHeight: TextInputEditText
    private lateinit var etWeight: TextInputEditText
    private lateinit var rgGoal: RadioGroup
    private lateinit var btnSave: Button
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupObservers()
        
        btnSave.setOnClickListener {
            saveProfile()
        }
    }

    private fun initViews(view: View) {
        etAge = view.findViewById(R.id.etAge)
        rgGender = view.findViewById(R.id.rgGender)
        etHeight = view.findViewById(R.id.etHeight)
        etWeight = view.findViewById(R.id.etWeight)
        rgGoal = view.findViewById(R.id.rgGoal)
        btnSave = view.findViewById(R.id.btnSaveProfile)
        progressBar = view.findViewById(R.id.progressBar)
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            btnSave.isEnabled = !isLoading
        }

        viewModel.saveStatus.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                Toast.makeText(context, "Profile Saved!", Toast.LENGTH_SHORT).show()
                // Navigate to Home
                findNavController().navigate(R.id.homeFragment)
            }.onFailure { e ->
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun saveProfile() {
        val ageStr = etAge.text.toString()
        val heightStr = etHeight.text.toString()
        val weightStr = etWeight.text.toString()

        if (TextUtils.isEmpty(ageStr) || TextUtils.isEmpty(heightStr) || TextUtils.isEmpty(weightStr)) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val age = ageStr.toIntOrNull()
        val height = heightStr.toDoubleOrNull()
        val weight = weightStr.toDoubleOrNull()

        if (age == null || height == null || weight == null) {
            Toast.makeText(context, "Invalid number format", Toast.LENGTH_SHORT).show()
            return
        }

        // Get Gender
        val selectedGenderId = rgGender.checkedRadioButtonId
        if (selectedGenderId == -1) {
            Toast.makeText(context, "Please select gender", Toast.LENGTH_SHORT).show()
            return
        }
        val gender = view?.findViewById<RadioButton>(selectedGenderId)?.text.toString()

        // Get Goal
        val selectedGoalId = rgGoal.checkedRadioButtonId
        val goalKey = when (selectedGoalId) {
            R.id.rbFatLoss -> "fat_loss"
            R.id.rbMuscleGain -> "muscle_gain"
            else -> "general_fitness"
        }

        viewModel.saveUserProfile(age, gender, height, weight, goalKey)
    }
}
