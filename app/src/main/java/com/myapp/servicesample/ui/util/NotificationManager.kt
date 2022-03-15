package com.myapp.servicesample.ui.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import androidx.core.app.NotificationCompat
import com.myapp.servicesample.R

/**
 * 通知処処理用クラス
 *
 */
class NotificationUtil {

    companion object {
        /**
         * 通知ID
         */
        var notificationId = 0
    }

    /**
     * 通知の種類
     *
     * @property id チャンネルID
     * @property channelName チャンネル名
     * @property title 通知タイトル
     * @property description 説明
     */
    enum class CHANNELS(val id: String, val channelName: String, val title: String, val description: String) {
        FOREGROUND_SERVICE_CHANNEL(
            "ForegroundService",
            "ForegroundService Sample",
            "ForegroundService",
            "ForegroundService用の通知"
        )
    }

    private lateinit var notificationManager: NotificationManager

    // ForegroundServiceのNotification取得
    fun getForegroundServiceNotification(context: Context) : Notification {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(CHANNELS.FOREGROUND_SERVICE_CHANNEL)
        return getNotification(CHANNELS.FOREGROUND_SERVICE_CHANNEL, "10秒経つまで待機します。", context)
    }


    // 通知設定　＆　通知表示
    private fun getNotification(channel: CHANNELS, message: String, context: Context) : Notification {
        return NotificationCompat.Builder(context, channel.id)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(channel.title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }

    // チャンネル登録
    private fun createNotificationChannel(channel: CHANNELS) {
        val notificationChannel = NotificationChannel(channel.id, channel.channelName, NotificationManager.IMPORTANCE_HIGH).also {
            it.enableLights(true)
            it.lightColor = Color.RED
            it.enableVibration(true)
            it.description = channel.description
        }
        notificationManager.createNotificationChannel(notificationChannel)
    }
}