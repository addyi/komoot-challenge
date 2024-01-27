package com.example.komoot.challenge.ui.main

import androidx.lifecycle.ViewModel
import com.example.komoot.challenge.services.notification.NotificationService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart

class MainViewModel(
    private val notificationService: NotificationService,
) : ViewModel() {
    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.MissingPermissions)
    val uiState =
        _uiState
            .onStart { notificationService.setupNotificationChannel() }

    fun onLocationPermissionGranted() {
        _uiState.value = MainUiState.Stopped
    }

    fun onStart() {
        _uiState.value = MainUiState.Running
    }

    fun onStop() {
        _uiState.value = MainUiState.Stopped
    }
}
