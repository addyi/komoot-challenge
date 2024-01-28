package com.example.komoot.challenge.services.waypoint

import android.location.Location
import com.example.komoot.challenge.data.local.Waypoint
import kotlinx.coroutines.flow.Flow

interface WaypointService {

    suspend fun newLocation(location: Location)

    fun getWaypoints(): Flow<List<Waypoint>>

    fun clearWaypoints()
}
