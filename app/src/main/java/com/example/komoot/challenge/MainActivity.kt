package com.example.komoot.challenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.komoot.challenge.services.foreground.LocationTrackingForegroundService
import com.example.komoot.challenge.services.notification.NotificationService
import com.example.komoot.challenge.ui.theme.AdrianRennerChallengeTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val notificationService: NotificationService by inject()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notificationService.setupNotificationChannel()

        // FIXME: improve permission handling
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
            ),
            0,
        )

        // FIXME: improve notification permission handling
        // Note: Apps don't need to request the POST_NOTIFICATIONS permission in order to launch
        // a foreground service. However, apps must include a notification when they start a
        // foreground service, just as they do on previous versions of Android.

        setContent {
            AdrianRennerChallengeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Hello Android") },
                            actions = {
                                IconButton(onClick = {
                                    Intent(
                                        applicationContext,
                                        LocationTrackingForegroundService::class.java,
                                    )
                                        .apply {
                                            action = LocationTrackingForegroundService.ACTION_START
                                        }
                                        .also(::startService)
                                }) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = "Start")
                                }
                            },
                        )
                    },
                ) {
                    Text(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(it),
                        text = "Hello Android",
                    )
                }
            }
        }
    }
}
