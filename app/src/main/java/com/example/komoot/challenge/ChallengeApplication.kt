package com.example.komoot.challenge

import android.app.Application
import com.example.komoot.challenge.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ChallengeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ChallengeApplication)
            modules(koinModule)
        }
    }
}
