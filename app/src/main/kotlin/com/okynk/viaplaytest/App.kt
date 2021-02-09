package com.okynk.viaplaytest

import androidx.multidex.MultiDexApplication
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import com.okynk.viaplaytest.injection.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber


@KoinApiExtension
class App : MultiDexApplication(), KoinComponent {

    private val networkFlipperPlugin: NetworkFlipperPlugin by inject()

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)

            // separate RxModule because we need to inject different one for UnitTest
            modules(appModules + rxModule)
        }

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            AndroidFlipperClient.getInstance(this).apply {
                addPlugin(networkFlipperPlugin)
                addPlugin(DatabasesFlipperPlugin(this@App))
                start()
            }
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
