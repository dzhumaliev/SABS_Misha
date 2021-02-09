package kg.sunrise.sabs.app
import kg.sunrise.sabs.data.FirstApi
import kg.sunrise.sabs.data.SecondApi
import org.koin.dsl.module
import retrofit2.Retrofit

private val retrofit1 : Retrofit =
    createNetworkClientPrayer1()

private val retrofit2 : Retrofit =
    createNetworkClientPrayer2()

private val API1: FirstApi = retrofit1.create(FirstApi::class.java)
private val API2: SecondApi = retrofit2.create(SecondApi::class.java)
val networkModule = module {
    single { API1 }
    single { API2 }
}