package com.example.komoot.challenge.services.storage

import android.content.Context
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.komoot.challenge.data.local.Waypoint
import com.example.komoot.challenge.data.local.WaypointDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class WaypointStorageImpl(context: Context) : WaypointStorage {
    private val db = WaypointDatabase(
        AndroidSqliteDriver(
            schema = WaypointDatabase.Schema,
            context = context,
            name = "location.db"
        )
    )

    override fun deleteAll() {
        db.waypointQueries.deleteAll()
    }

    override fun getWaypoints(): Flow<List<Waypoint>> {
        return db.waypointQueries.getAll().asFlow().mapToList(Dispatchers.IO)
    }

    override fun getLatestWaypoint(): Waypoint? {
        return db.waypointQueries.getLatest().executeAsOneOrNull()
    }

    override fun insert(waypoint: Waypoint) {
        db.waypointQueries.transaction {
            db.waypointQueries.insert(waypoint.time, waypoint.latitude, waypoint.longitude, waypoint.picture)
        }
    }
}
