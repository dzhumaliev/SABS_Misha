package kg.sunrise.sabs.app

import android.util.Log
import kg.sunrise.sabs.BuildConfig
import kg.sunrise.sabs.utils.UserInfoPref
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val sLogLevel =
    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

 var baseUrlAman = "http://51.254.69.252/"
 var baseUrlKanat = "http://5.196.129.104:40003/api/"
// var token = ""

private fun getLogInterceptor() = HttpLoggingInterceptor().apply { level =
    sLogLevel
}
fun createNetworkClientPrayer1() =
    retrofitClient(
        baseUrlAman,
        okHttpClient(true)
    )
fun createNetworkClientPrayer2() =
    retrofitClient(
        baseUrlKanat,
        okHttpClient(true)
    )
private fun okHttpClient(addAuthHeader: Boolean) = OkHttpClient.Builder()
    .addInterceptor(getLogInterceptor()).apply { setTimeOutToOkHttpClient(this)
    }.addInterceptor(headersInterceptor(addAuthHeader)).build()

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun headersInterceptor(addAuthHeader: Boolean) = Interceptor { chain ->
    chain.proceed(
        chain.request().newBuilder()
            .addHeader("Content-Type", "application/json;charset=UTF-8")
            .addHeader("Accept", "application/json")
            .also {
                if (addAuthHeader) {
//                    it.addHeader("Authorization", token)
//                    it.addHeader("Authentication", UserInfoPref.getAccessToken(GettingStartApplication.instance))
                    it.addHeader("Authorization", UserInfoPref.getAccessToken(GettingStartApplication.instance))
                }
            }.build()
    )
}
private fun setTimeOutToOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder) =
    okHttpClientBuilder.apply {
        readTimeout(30L, TimeUnit.SECONDS)
        connectTimeout(30L, TimeUnit.SECONDS)
        writeTimeout(30L, TimeUnit.SECONDS)
    }