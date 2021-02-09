package kg.sunrise.sabs.app

import kg.sunrise.sabs.data.FirstRepo
import kg.sunrise.sabs.data.SecondRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module{
    single { FirstRepo(androidContext(), firstApi = get()) }
    single { SecondRepo(androidContext(), secondApi = get()) }
}