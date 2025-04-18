package com.example.komoot.challenge.services.waypoint.storage

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
            db.waypointQueries.insert(
                time = waypoint.time,
                latitude = waypoint.latitude,
                longitude = waypoint.longitude,
                picture = waypoint.picture,
                title = waypoint.title
            )
        }
    }
}
