package com.example.komoot.challenge.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.komoot.challenge.services.waypoint.WaypointService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(
    private val waypointService: WaypointService
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.MissingPermissions)
    val uiState = _uiState

    fun onLocationPermissionGranted() {
        waypointService
            .getWaypoints()
            .onEach { waypoints ->
                if (waypoints.isEmpty()) {
                    _uiState.value = MainUiState.Stopped
                } else {
                    _uiState.value = MainUiState.Running(waypoints.reversed())
                }
            }
            .launchIn(viewModelScope)
    }
}
