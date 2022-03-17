package com.myapp.servicesample.ui.util

import android.content.Intent

import android.app.Service
import android.os.Binder
import android.os.IBinder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

/**
 * BindServiceサンプル
 *
 */
class BindService : Service() {

    // Bind Service　インスタンス化
    private val binder = LocalBinder()

    fun startTenCount() {
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
        }
    }

    inner class LocalBinder : Binder() {
        fun getService(): BindService = this@BindService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}