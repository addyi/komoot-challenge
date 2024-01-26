package com.example.komoot.challenge.di

import android.content.Context
import com.example.komoot.challenge.services.location.LocationClient
import com.example.komoot.challenge.services.location.LocationClientImpl
import com.example.komoot.challenge.services.notification.NotificationService
import com.example.komoot.challenge.services.notification.NotificationServiceImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val koinModule =
    module {

        factory<FusedLocationProviderClient> {
            LocationServices.getFusedLocationProviderClient(get<Context>())
        }
        singleOf(::LocationClientImpl) { bind<LocationClient>() }
        singleOf(::NotificationServiceImpl) { bind<NotificationService>() }
    }
