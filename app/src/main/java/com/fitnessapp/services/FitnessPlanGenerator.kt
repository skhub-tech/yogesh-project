package com.fitnessapp.services

import com.fitnessapp.model.DailyWorkout
import com.fitnessapp.model.PlanExercise
import com.fitnessapp.model.User
import com.fitnessapp.model.WeeklySchedule

object FitnessPlanGenerator {

    fun generatePlan(user: User): WeeklySchedule {
        return when (user.fitnessGoal) {
            "fat_loss" -> generateFatLossPlan()
            "muscle_gain" -> generateMuscleGainPlan()
            else -> generateGeneralFitnessPlan()
        }
    }

    private fun generateFatLossPlan(): WeeklySchedule {
        return WeeklySchedule(
            monday = DailyWorkout("Monday", "HIIT & Core", listOf(
                PlanExercise("Jumping Jacks", 3, "45 sec"),
                PlanExercise("Burpees", 3, "15 reps"),
                PlanExercise("Mountain Climbers", 3, "45 sec"),
                PlanExercise("Plank", 3, "60 sec")
            )),
            tuesday = DailyWorkout("Tuesday", "Full Body Strength", listOf(
                PlanExercise("Push-ups", 3, "15 reps"),
                PlanExercise("Squats", 3, "20 reps"),
                PlanExercise("Lunges", 3, "15 each leg")
            )),
            wednesday = DailyWorkout("Wednesday", "Active Recovery", isRestDay = true),
            thursday = DailyWorkout("Thursday", "Cardio Blast", listOf(
                PlanExercise("Running / Jogging", 1, "30 mins"),
                PlanExercise("Jump Rope", 5, "1 min")
            )),
            friday = DailyWorkout("Friday", "Lower Body & Core", listOf(
                PlanExercise("Glute Bridges", 3, "20 reps"),
                PlanExercise("Leg Raises", 3, "15 reps"),
                PlanExercise("Side Planks", 3, "30 sec each")
            )),
            saturday = DailyWorkout("Saturday", "Long Walk / Hike", isRestDay = true), 
            sunday = DailyWorkout("Sunday", "Rest", isRestDay = true)
        )
    }

    private fun generateMuscleGainPlan(): WeeklySchedule {
        return WeeklySchedule(
             monday = DailyWorkout("Monday", "Push (Chest, Shoulders, Triceps)", listOf(
                PlanExercise("Push-ups / Bench Press", 4, "8-12"),
                PlanExercise("Overhead Press", 3, "10-12"),
                PlanExercise("Tricep Dips", 3, "12-15")
            )),
             tuesday = DailyWorkout("Tuesday", "Pull (Back, Biceps)", listOf(
                PlanExercise("Pull-ups / Rows", 4, "8-12"),
                PlanExercise("Bicep Curls", 3, "12-15"),
                PlanExercise("Face Pulls", 3, "15")
            )),
            wednesday = DailyWorkout("Wednesday", "Legs", listOf(
                PlanExercise("Squats", 4, "8-10"),
                PlanExercise("Lunges", 3, "12 each"),
                PlanExercise("Calf Raises", 4, "15")
            )),
            thursday = DailyWorkout("Thursday", "Rest", isRestDay = true),
             friday = DailyWorkout("Friday", "Upper Body", listOf(
                PlanExercise("Pull-ups", 3, "Max"),
                PlanExercise("Push-ups", 3, "Max"),
                PlanExercise("Shoulder Press", 3, "12")
            )),
            saturday = DailyWorkout("Saturday", "Lower Body", listOf(
                PlanExercise("Goblet Squats", 3, "12"),
                PlanExercise("Deadlifts / RDL", 3, "10")
            )),
            sunday = DailyWorkout("Sunday", "Rest", isRestDay = true)
        )
    }

    private fun generateGeneralFitnessPlan(): WeeklySchedule {
        return WeeklySchedule(
            monday = DailyWorkout("Monday", "Circuit Training", listOf(
                PlanExercise("Push-ups", 3, "10"),
                PlanExercise("Squats", 3, "15"),
                PlanExercise("Plank", 3, "30 sec")
            )),
            tuesday = DailyWorkout("Tuesday", "Cardio", listOf(
                PlanExercise("Brisk Walking / Jogging", 1, "30 mins")
            )),
            wednesday = DailyWorkout("Wednesday", "Rest", isRestDay = true),
            thursday = DailyWorkout("Thursday", "Full Body", listOf(
                PlanExercise("Lunges", 3, "12"),
                PlanExercise("Dumbbell Rows", 3, "12"),
                PlanExercise("Shoulder Taps", 3, "20")
            )),
             friday = DailyWorkout("Friday", "Mobility & Core", listOf(
                PlanExercise("Yoga / Stretching", 1, "20 mins"),
                PlanExercise("Crunches", 3, "20")
            )),
             saturday = DailyWorkout("Saturday", "Outdoor Activity", listOf(
                 PlanExercise("Sports / Hiking", 1, "60 mins")
             )),
            sunday = DailyWorkout("Sunday", "Rest", isRestDay = true)
        )
    }
}
