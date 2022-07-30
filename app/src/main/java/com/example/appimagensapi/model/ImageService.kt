package com.example.appimagensapi.model


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


interface ImageService {

    @GET("/gallery/search/?q=cats")
    suspend fun getCats(): ImagesResponse

}