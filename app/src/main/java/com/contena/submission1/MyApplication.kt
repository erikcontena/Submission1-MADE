package com.contena.submission1

import android.app.Application
import com.contena.core.di.databaseModule
import com.contena.core.di.networkModule
import com.contena.core.di.repositoryModule
import com.contena.submission1.ui.di.useCaseModule
import com.contena.submission1.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}