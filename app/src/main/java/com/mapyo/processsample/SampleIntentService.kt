package com.mapyo.processsample


import android.app.IntentService
import android.content.Intent

class SampleIntentService : IntentService("SampleIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        (1..15).map {
            Thread.sleep(1000)
            showMessage("SampleIntentService counter: " + it)
        }
    }

    private fun showMessage(message: Any?) {
        val threadName = Thread.currentThread().name
        println(threadName + ":" + message)
    }
}
