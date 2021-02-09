package kg.sunrise.sabs.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GettingStartApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        instance = this@GettingStartApplication
        startKoin {
        androidLogger()
        androidContext(this@GettingStartApplication)
        modules(listOf(
            repositoryModule,
            networkModule,
            viewModelModule
        )) }
    }
    companion object {
        lateinit var instance: GettingStartApplication
    }
}