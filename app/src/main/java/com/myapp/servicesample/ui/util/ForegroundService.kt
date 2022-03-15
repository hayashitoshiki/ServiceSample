package com.myapp.servicesample.ui.util

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * ForegroundService サンプル
 *
 */
class ForegroundService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    @DelicateCoroutinesApi
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("ForegroundService","onStartCommand")

        // Start
        val notification = NotificationUtil().getForegroundServiceNotification(this)
        startForeground(1, notification)

        // Action
        GlobalScope.launch {
            var count = 0
            while (count < 10) {
                delay(1000L)
                count += 1
            }
            // 終了
            stopForeground(true)
        }

        return START_STICKY
    }
}