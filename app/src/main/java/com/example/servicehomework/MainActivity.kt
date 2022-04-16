package com.example.servicehomework

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.servicehomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceClass = RandomNumbersService::class.java

        val intent = Intent(this, serviceClass)

        binding.buttonStart.setOnClickListener {
            if (!isServiceRunning(serviceClass)) {
                startService(intent)
            } else {
                toast("Service already running.")
            }
        }

        binding.buttonStop.setOnClickListener {
            if (isServiceRunning(serviceClass)) {
                stopService(intent)
            } else {
                toast("Service already stopped.")
            }
        }
    }

    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}

fun Context.toast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}


