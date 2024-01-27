package com.example.komoot.challenge.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainMissingPermissionScreen(
    onRequestPermission: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { MainTopBar() },
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(it),
        ) {
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onRequestPermission,
            ) {
                Text(text = "Request permission")
            }
        }
    }
}

@Composable
fun MainStoppedScreen(
    onStart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainTopBar {
                TextButton(
                    onClick = onStart,
                    content = { Text("Start") },
                )
            }
        },
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(it),
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Please start the service!",
            )
        }
    }
}

@Composable
fun MainPictureFeedScreen(
    onStop: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainTopBar {
                TextButton(
                    onClick = onStop,
                    content = { Text("Stop") },
                )
            }
        },
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(it),
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "TODO: Picture feed",
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainTopBar(
    modifier: Modifier = Modifier,
    action: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = "Hello komoot!") },
        actions = action,
    )
}
