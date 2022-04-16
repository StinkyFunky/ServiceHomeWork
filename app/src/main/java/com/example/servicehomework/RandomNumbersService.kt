package com.example.servicehomework

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import java.util.*

class RandomNumbersService: Service() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        handler = Handler()
        runnable = Runnable { showRandomNumber() }
        handler.postDelayed(runnable, 5000)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        toast("Service destroyed.")
        handler.removeCallbacks(runnable)
    }

    private fun showRandomNumber() {
        val rand = Random()
        val number = rand.nextInt(100)
        toast("Random Number : $number")
        handler.postDelayed(runnable, 5000)
    }
}