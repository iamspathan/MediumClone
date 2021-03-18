package dev.iamspathan.realworldapi

import dev.iamspathan.realworldapi.services.ConduitAPIService
import dev.iamspathan.realworldapi.services.ConduitAuthAPI
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Header
import java.sql.Time
import java.util.concurrent.TimeUnit.SECONDS

object ConduitClient {

    val BASE_URL = "https://conduit.productionready.io/api/"


    var authToken : String? = null

    private val okHttpInterceptor = Interceptor { chain: Chain ->
        var req = chain.request()
        authToken?.let {
           req = req.newBuilder()
               .header("Authorization", "Token $it")
               .build()
        }
        chain.proceed(req)
    }


    val okhttpBuilder = OkHttpClient.Builder()
        .readTimeout(5, SECONDS)
        .callTimeout(5, SECONDS)
        .connectTimeout(5,SECONDS)



    val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())



    val publicApi = retrofitBuilder
        .client(okhttpBuilder.build())
        .build()
        .create(ConduitAPIService::class.java)

    val authApi = retrofitBuilder
        .client(okhttpBuilder.addInterceptor(okHttpInterceptor).build())
        .build()
        .create(ConduitAuthAPI::class.java)

}