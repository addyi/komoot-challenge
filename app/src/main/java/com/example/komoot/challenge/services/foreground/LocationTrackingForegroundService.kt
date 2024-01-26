package com.example.komoot.challenge.services.foreground

import android.app.Service
import android.content.Intent
import android.util.Log
import com.example.komoot.challenge.services.location.LocationClient
import com.example.komoot.challenge.services.notification.NotificationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

class LocationTrackingForegroundService : Service() {
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val locationClient: LocationClient by inject()
    private val notificationService: NotificationService by inject()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate() called")
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int,
    ): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent) = null

    override fun onDestroy() {
        serviceScope.cancel()
        super.onDestroy()
    }

    private fun start() {
        Log.d(TAG, "start() called")
        val notification = notificationService.getForegroundNotification(this)

        locationClient.getLastKnownLocation()
            .catch { e -> e.printStackTrace() }
            .onEach { location -> Log.d(TAG, "start: $location") }
            .launchIn(serviceScope)

        startForeground(1, notification)
    }

    private fun stop() {
        Log.d(TAG, "stop() called")
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    companion object {
        private const val TAG = "LocationTrackingService"

        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}
