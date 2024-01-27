package com.example.komoot.challenge.di

import android.content.Context
import com.example.komoot.challenge.services.location.LocationClient
import com.example.komoot.challenge.services.location.LocationClientImpl
import com.example.komoot.challenge.services.notification.NotificationService
import com.example.komoot.challenge.services.notification.NotificationServiceImpl
import com.example.komoot.challenge.services.storage.WaypointStorage
import com.example.komoot.challenge.services.storage.WaypointStorageImpl
import com.example.komoot.challenge.services.waypoint.WaypointService
import com.example.komoot.challenge.services.waypoint.WaypointServiceImpl
import com.example.komoot.challenge.ui.main.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val koinModule =
    module {

        viewModelOf(::MainViewModel)

        factory<FusedLocationProviderClient> {
            LocationServices.getFusedLocationProviderClient(get<Context>())
        }
        singleOf(::LocationClientImpl) { bind<LocationClient>() }
        singleOf(::NotificationServiceImpl) { bind<NotificationService>() }
        singleOf(::WaypointStorageImpl) { bind<WaypointStorage>() }
        singleOf(::WaypointServiceImpl) { bind<WaypointService>() }
    }
