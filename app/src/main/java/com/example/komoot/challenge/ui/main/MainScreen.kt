package com.example.komoot.challenge.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.komoot.challenge.data.local.Waypoint

@Composable
fun MainMissingPermissionScreen(onRequestPermission: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { MainTopBar() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onRequestPermission
            ) {
                Text(text = "Request permission")
            }
        }
    }
}

@Composable
fun MainStoppedScreen(onStart: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainTopBar {
                TextButton(
                    onClick = onStart,
                    content = { Text("Start") }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Please start the service!"
            )
        }
    }
}

@Composable
fun MainPictureFeedScreen(
    onStop: () -> Unit,
    waypoints: List<Waypoint>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainTopBar {
                TextButton(
                    onClick = onStop,
                    content = { Text("Stop") }
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            items(waypoints) { waypoint ->
                Picture(waypoint = waypoint)
            }
        }
    }
}

@Composable
private fun Picture(waypoint: Waypoint, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            style = MaterialTheme.typography.headlineSmall,
            text = waypoint.title ?: "Unknown"
        )

        Text(text = "Latitude: ${waypoint.latitude}")
        Text(text = "Longitude: ${waypoint.longitude}")

        waypoint.picture?.let { url ->
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = url,
                contentScale = ContentScale.FillWidth,
                contentDescription = waypoint.title
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainTopBar(modifier: Modifier = Modifier, action: @Composable RowScope.() -> Unit = {}) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = "Hello komoot!") },
        actions = action
    )
}
