package com.example.appimagensapi.model

import androidx.viewbinding.BuildConfig
import com.example.appimagensapi.BuildConfig.API_KEY
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetWorkModule {
    inline fun <reified T> createNetworkService(): T {


        val log = HttpLoggingInterceptor()
        log.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient
            .Builder()
            .addInterceptor { chain ->

                val originalRequest = chain.request()
                val originalUrl = originalRequest.url
                val newUrl = originalUrl.newBuilder()
                    .build()

                val newRequest = originalRequest.newBuilder().url(newUrl).build()

                chain.proceed(
                    newRequest
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "Client-ID 1ceddedc03a5d71")
                        .build()
                )
            }

            .addInterceptor(log)
            .build()



        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.imgur.com/3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        return retrofit.create(T::class.java)
    }
}