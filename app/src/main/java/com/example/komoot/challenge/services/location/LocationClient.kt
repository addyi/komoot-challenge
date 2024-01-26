package com.example.komoot.challenge.services.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLastKnownLocation(): Flow<Location>
}
