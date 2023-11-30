package com.example.task

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val interceptor=HttpLoggingInterceptor()
val client=OkHttpClient.Builder().addInterceptor(interceptor).build()
val retrofit= Retrofit.Builder()
    .baseUrl("https://dummyjson.com/")  .client(client)
    .addConverterFactory(GsonConverterFactory.create()).build()
val productApi= retrofit.create(ProductApi::class.java)
  fun level(){ interceptor.level=HttpLoggingInterceptor.Level.BODY }