package com.example.komoot.challenge.services.waypoint

import android.location.Location
import com.example.komoot.challenge.data.local.Waypoint
import com.example.komoot.challenge.services.storage.WaypointStorage
import kotlinx.coroutines.flow.Flow

class WaypointServiceImpl(
    private val waypointStorage: WaypointStorage
) : WaypointService {

    override fun newLocation(location: Location) {
        val latestWaypoint = waypointStorage.getLatestWaypoint()

        if (latestWaypoint == null) {
            fetchUrlAndStore(location)
        } else {
            val distance = location.distanceTo(latestWaypoint.toLocation())

            if (distance >= waypointDistanceInMeter) {
                fetchUrlAndStore(location)
            }
        }
    }

    override fun getWaypoints(): Flow<List<Waypoint>> = waypointStorage.getWaypoints()

    override fun clearWaypoints() {
        waypointStorage.deleteAll()
    }

    private fun fetchUrlAndStore(location: Location) {
        // FIXME: fetch url
        waypointStorage.insert(
            Waypoint(
                time = System.currentTimeMillis(),
                latitude = location.latitude,
                longitude = location.longitude,
                picture = "FIXME"
            )
        )
    }

    private fun Waypoint.toLocation(): Location {
        return Location(null).apply {
            latitude = this@toLocation.latitude
            longitude = this@toLocation.longitude
        }
    }

    companion object {
        private const val waypointDistanceInMeter = 100
    }
}
