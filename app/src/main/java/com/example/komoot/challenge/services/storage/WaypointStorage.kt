package com.example.komoot.challenge.services.storage

import com.example.komoot.challenge.data.local.Waypoint
import kotlinx.coroutines.flow.Flow

interface WaypointStorage {

    fun insert(waypoint: Waypoint)

    fun getWaypoints(): Flow<List<Waypoint>>

    fun getLatestWaypoint(): Waypoint?

    fun deleteAll()
}
