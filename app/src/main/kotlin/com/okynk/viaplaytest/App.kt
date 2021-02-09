package com.okynk.viaplaytest

import androidx.multidex.MultiDexApplication
import com.jakewharton.threetenabp.AndroidThreeTen
import com.okynk.viaplaytest.injection.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(appModules)
        }
    }
}

// Gather app's all modules
val appModules = listOf(
    dataSourceModule,
    repositoryModule,
    useCaseModule,
    utilModule,
    viewModelModule,
    mapperModule,
    apiModule,
    databaseModule
)
