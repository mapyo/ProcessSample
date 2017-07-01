package com.mapyo.processsample


import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Process
import java.util.*

class SampleService : Service() {
    private val TAG = this.javaClass.simpleName!!

    private val timer: Timer = Timer()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        showMessage("create service")

        var counter = 0
        timer.schedule(object : TimerTask() {
            override fun run() {
                counter++
                showMessage("counter: " + counter)
                if (counter == 10) {
                    Handler(Looper.getMainLooper()).post {
                        showMessage("counter finished")
                        timer.cancel()
                    }
                }
            }

        }, 1_000, 1_000)

        Handler(Looper.getMainLooper()).postDelayed({
            showMessage("stop service")
            this.stopService(Intent(this, SampleService::class.java))
        }, 5_000)

        this.startService(Intent(this, SampleIntentService::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        showMessage("destroy service")

        Process.killProcess(Process.myPid())
    }

    private fun showMessage(message: Any?) {
        val threadName = Thread.currentThread().name
        println(threadName + ":" + message)
    }
}
