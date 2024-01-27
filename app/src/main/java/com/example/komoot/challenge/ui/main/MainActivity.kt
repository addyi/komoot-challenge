package com.example.komoot.challenge.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import com.example.komoot.challenge.services.foreground.LocationTrackingForegroundService
import com.example.komoot.challenge.services.notification.NotificationService
import com.example.komoot.challenge.ui.theme.AdrianRennerChallengeTheme
import com.example.komoot.challenge.util.hasLocationPermission
import com.example.komoot.challenge.util.hasNotificationPermission
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val notificationService: NotificationService by inject()

    private val viewModel: MainViewModel by viewModel()

    private val permissions by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.POST_NOTIFICATIONS
            )
        } else {
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { resultMap ->
            resultMap.values
                .none { isGranted -> !isGranted }
                .let { allGranted ->
                    if (allGranted) {
                        viewModel.onLocationPermissionGranted()
                    } else {
                        Toast.makeText(
                            this,
                            "All permissions are required",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notificationService.setupNotificationChannel()

        setContent {
            AdrianRennerChallengeTheme {
                val uiState =
                    viewModel.uiState.collectAsState(initial = MainUiState.MissingPermissions)

                when (val state = uiState.value) {
                    MainUiState.MissingPermissions ->
                        MainMissingPermissionScreen(onRequestPermission = ::requestPermissions)

                    MainUiState.Stopped -> MainStoppedScreen(onStart = ::startForegroundService)

                    is MainUiState.Running -> MainPictureFeedScreen(
                        onStop = ::stopForegroundService,
                        waypoints = state.waypoints
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (hasLocationPermission() && hasNotificationPermission()) {
            viewModel.onLocationPermissionGranted()
        }
    }

    private fun requestPermissions() {
        requestPermissionLauncher.launch(permissions)
    }

    private fun startForegroundService() {
        Intent(this, LocationTrackingForegroundService::class.java)
            .apply { action = LocationTrackingForegroundService.ACTION_START }
            .also(this::startService)
    }

    private fun stopForegroundService() {
        Intent(this, LocationTrackingForegroundService::class.java)
            .apply { action = LocationTrackingForegroundService.ACTION_STOP }
            .also(this::startService)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
