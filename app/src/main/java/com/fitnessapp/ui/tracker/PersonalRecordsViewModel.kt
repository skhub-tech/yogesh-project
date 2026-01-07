package com.fitnessapp.ui.tracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitnessapp.model.ExerciseSetLog
import com.fitnessapp.repository.TrackerRepository
import com.fitnessapp.repository.UserRepository
import kotlinx.coroutines.launch

data class PersonalRecord(
    val exerciseName: String,
    val bestWeight: Double,
    val bestReps: Int,
    val estimated1RM: Double
)

class PersonalRecordsViewModel : ViewModel() {
    private val trackerRepository = TrackerRepository()
    private val userRepository = UserRepository()

    private val _records = MutableLiveData<List<PersonalRecord>>()
    val records: LiveData<List<PersonalRecord>> get() = _records

    init {
        loadPRs()
    }

    private fun loadPRs() {
        viewModelScope.launch {
            val uid = userRepository.getCurrentUserUid()
            if (uid != null) {
                val result = trackerRepository.getWorkoutLogs(uid)
                if (result.isSuccess) {
                    val logs = result.getOrNull() ?: emptyList()
                    _records.value = calculatePRs(logs)
                }
            }
        }
    }

    private fun calculatePRs(logs: List<ExerciseSetLog>): List<PersonalRecord> {
        return logs.groupBy { it.exerciseName }.map { (name, exerciseLogs) ->
            val bestLog = exerciseLogs.maxByOrNull { calculate1RM(it.weight, it.reps) }!!
            PersonalRecord(
                exerciseName = name,
                bestWeight = bestLog.weight,
                bestReps = bestLog.reps,
                estimated1RM = calculate1RM(bestLog.weight, bestLog.reps)
            )
        }
    }

    private fun calculate1RM(weight: Double, reps: Int): Double {
        if (reps == 0) return 0.0
        // Epley format: 1RM = weight * (1 + reps/30.0)
        return weight * (1 + reps / 30.0)
    }
}
