package com.example.komoot.challenge.services.notification

import android.app.Notification
import android.content.Context

interface NotificationService {
    fun setupNotificationChannel()

    fun getForegroundNotification(context: Context): Notification
}
