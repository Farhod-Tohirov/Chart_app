package com.star.chartapplesson15.data.restApi

import com.readystatesoftware.chuck.ChuckInterceptor
import com.star.chartapplesson15.app.App
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://33b34f421259.ngrok.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val okHttpClientWithChuck = OkHttpClient.Builder()
        .addInterceptor(ChuckInterceptor(App.instance))
        .build()

    fun <T> createService(service: Class<T>): T {
        val newBuilder = retrofit.newBuilder().client(okHttpClientWithChuck).build()
        return newBuilder.create(service)
    }
}
