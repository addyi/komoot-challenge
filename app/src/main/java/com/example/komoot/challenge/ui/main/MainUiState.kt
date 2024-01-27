package com.example.komoot.challenge.ui.main

sealed interface MainUiState {
    data object MissingPermissions : MainUiState

    data object Stopped : MainUiState

    data object Running : MainUiState
}
