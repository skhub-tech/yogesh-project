package com.fitnessapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object AppExpirationManager {

    // REPLACE THIS WITH YOUR ACTUAL GITHUB RAW URL
    // Example: "https://raw.githubusercontent.com/YourUsername/YourRepo/main/app_config.json"
    private const val CONFIG_URL = "https://pastebin.com/raw/DkMhQvFS" 

    fun checkAppExpiration(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL(CONFIG_URL)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 5000
                connection.readTimeout = 5000

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val stream = connection.inputStream
                    val response = stream.bufferedReader().use { it.readText() }
                    val json = JSONObject(response)

                    val isExpired = json.optBoolean("is_expired", false)
                    val message = json.optString("message", "This testing version has expired. Please contact the developer.")
                    val whatsappNumber = json.optString("whatsapp_number", "")

                    if (isExpired) {
                        withContext(Dispatchers.Main) {
                            handleExpiration(context, whatsappNumber)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("AppExpiration", "Failed to check expiration", e)
                // Fail silently so the app works offline
            }
        }
    }



    private fun handleExpiration(context: Context, whatsappNumber: String) {
        // 1. Show Toast
        Toast.makeText(context, "Pay your pending due", Toast.LENGTH_LONG).show()

        // 2. Redirect to WhatsApp
        openWhatsApp(context, whatsappNumber)

        // 3. Force Crash after slight delay to allow Toast/Intent to start
        Handler(Looper.getMainLooper()).postDelayed({
            throw RuntimeException("Force Crash: Application Expired - Payment Due")
        }, 2000)
    }

    private fun openWhatsApp(context: Context, number: String) {
        try {
            // Clean number
            val cleanNumber = number.replace("+", "").replace(" ", "")
            val url = "https://wa.me/$cleanNumber"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK // Required when starting from non-activity context if application context is used
            context.startActivity(intent)
        } catch (e: Exception) {
            // Fallback
             val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.whatsapp"))
             intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
             context.startActivity(intent)
        }
    }
}
