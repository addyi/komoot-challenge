package com.example.komoot.challenge.ui.main

import com.example.komoot.challenge.data.local.Waypoint

sealed interface MainUiState {
    data object MissingPermissions : MainUiState

    data object Stopped : MainUiState

    data class Running(val waypoints: List<Waypoint>) : MainUiState
}
