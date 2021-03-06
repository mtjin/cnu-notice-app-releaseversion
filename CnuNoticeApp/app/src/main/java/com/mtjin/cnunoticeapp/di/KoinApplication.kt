package com.mtjin.cnunoticeapp.di

import android.app.Application
import com.mtjin.cnunoticeapp.BuildConfig
import com.mtjin.cnunoticeapp.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@KoinApplication)
            modules(
                repositoryModule,
                localDataModule,
                remoteDataModule,
                viewModelModule,
                apiModule,
                firebaseModule
            )

        }
    }
}