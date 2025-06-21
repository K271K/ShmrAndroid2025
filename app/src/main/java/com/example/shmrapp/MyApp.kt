package com.example.shmrapp

import android.app.Application
import com.example.shmrapp.di.appModule
import com.example.shmrapp.di.dataModule
import com.example.shmrapp.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}