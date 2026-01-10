package com.fitnessapp.ui.tracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitnessapp.model.DailyLog
import com.fitnessapp.repository.TrackerRepository
import com.fitnessapp.repository.UserRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TrackerViewModel : ViewModel() {

    private val trackerRepository = TrackerRepository()
    private val userRepository = UserRepository()

    private val _logs = MutableLiveData<List<DailyLog>>()
    val logs: LiveData<List<DailyLog>> get() = _logs

    private val _saveStatus = MutableLiveData<Result<Boolean>>()
    val saveStatus: LiveData<Result<Boolean>> get() = _saveStatus

    private val _sessions = MutableLiveData<List<com.fitnessapp.model.WorkoutSession>>()
    val sessions: LiveData<List<com.fitnessapp.model.WorkoutSession>> get() = _sessions

    private val _volumeData = MutableLiveData<List<Pair<String, Double>>>()
    val volumeData: LiveData<List<Pair<String, Double>>> get() = _volumeData

    private val _totalWorkouts = MutableLiveData<Int>()
    val totalWorkouts: LiveData<Int> get() = _totalWorkouts

    private val _maxVolume = MutableLiveData<Double>()
    val maxVolume: LiveData<Double> get() = _maxVolume

    init {
        loadLogs()
        loadWorkoutSessions()
    }

    private fun loadWorkoutSessions() {
        viewModelScope.launch {
            val uid = userRepository.getCurrentUserUid()
            if (uid != null) {
                val result = trackerRepository.getWorkoutLogs(uid)
                if (result.isSuccess) {
                    val logs = result.getOrNull() ?: emptyList()
                    val groupedSessions = groupLogsIntoSessions(logs)
                    _sessions.value = groupedSessions
                    
                    // Identify max volume
                    val volumeList = calculateDailyVolume(groupedSessions)
                    _volumeData.value = volumeList
                    
                    _totalWorkouts.value = groupedSessions.size
                    _maxVolume.value = volumeList.maxOfOrNull { it.second } ?: 0.0
                }
            }
        }
    }

    private fun calculateDailyVolume(sessions: List<com.fitnessapp.model.WorkoutSession>): List<Pair<String, Double>> {
        // sessions are currently Descending by date (newest first)
        // We want chart to be Oldest -> Newest (Ascending)
        return sessions.map { session ->
            val totalVolume = session.exercises.sumOf { ex ->
                ex.sets.sumOf { set -> set.weight * set.reps }
            }
            Pair(session.date, totalVolume)
        }.sortedBy { it.first } // Sort by date ascending
    }

    private fun groupLogsIntoSessions(logs: List<com.fitnessapp.model.ExerciseSetLog>): List<com.fitnessapp.model.WorkoutSession> {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        
        return logs.groupBy { 
            sdf.format(Date(it.timestamp)) 
        }.map { (date, dayLogs) ->
            val exercisesWithSets = dayLogs.groupBy { it.exerciseName }.map { (name, sets) ->
                com.fitnessapp.model.ExerciseWithSets(
                    exerciseName = name,
                    sets = sets.sortedBy { it.timestamp }
                )
            }
            com.fitnessapp.model.WorkoutSession(
                date = date,
                exercises = exercisesWithSets
            )
        }.sortedByDescending { it.date } // List view wants Newest first
    }

    fun loadLogs() {
        viewModelScope.launch {
            val uid = userRepository.getCurrentUserUid()
            if (uid != null) {
                val result = trackerRepository.getLast7DaysLogs(uid)
                if (result.isSuccess) {
                    // Repository returns Descending. We need Ascending for Chart.
                    val rawList = result.getOrNull() ?: emptyList()
                    _logs.value = rawList.sortedBy { it.date }
                }
            }
        }
    }

    fun saveLog(weight: Double) {
        viewModelScope.launch {
            val uid = userRepository.getCurrentUserUid()
            if (uid != null) {
                val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                val log = DailyLog(
                    date = date,
                    weight = weight
                )
                val result = trackerRepository.saveDailyLog(uid, log)
                _saveStatus.value = result
                if (result.isSuccess) {
                    loadLogs() // Refresh
                }
            }
        }
    }

    fun logSet(exerciseName: String, weight: Double, reps: Int) {
        viewModelScope.launch {
            val uid = userRepository.getCurrentUserUid()
            if (uid != null) {
                val log = com.fitnessapp.model.ExerciseSetLog(
                    id = java.util.UUID.randomUUID().toString(),
                    userId = uid,
                    exerciseName = exerciseName,
                    weight = weight,
                    reps = reps,
                    timestamp = System.currentTimeMillis() // Ensure timestamp is set
                )
                trackerRepository.logExerciseSet(uid, log)
                loadWorkoutSessions()
            }
        }
    }
    fun getCsvContent(): String {
        val logs = _sessions.value ?: return "No data"
        val header = "Date,Exercise,Weight,Reps\n"
        val rows = logs.flatMap { session ->
            session.exercises.flatMap { ex ->
                ex.sets.map { set ->
                    "${session.date},${ex.exerciseName},${set.weight},${set.reps}"
                }
            }
        }.joinToString("\n")
        return header + rows
    }
}
