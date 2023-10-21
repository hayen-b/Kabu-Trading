package kabu.hayen.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import kabu.hayen.BuildConfig
import kabu.hayen.module.appModule

class KabuApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        val modules = listOf(
            appModule
        )

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@KabuApplication)
            modules(modules)
        }
    }
}