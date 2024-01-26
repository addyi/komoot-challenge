package com.example.komoot.challenge.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.hasLocationPermission(): Boolean {
    val coarsePermission =
        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
    val finePermission =
        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

    return coarsePermission == PackageManager.PERMISSION_GRANTED &&
        finePermission == PackageManager.PERMISSION_GRANTED
}
