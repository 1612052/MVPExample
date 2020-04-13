package com.chienpm.mvpexample.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://my-json-server.typicode.com/"
const val TYPICO_PROJECT = "/1612052/fk_jsonserver"

interface RetrofitService {

    companion object Factory {
        var logging   = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

        // OkHttpClient only run on Java 8, please set source compatibility to 1.8
        //https://developer.android.com/studio/write/java8-support
        var client : OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

        private var retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fun <S> createService(serviceClass: Class<S>): S {
            return retrofit.create(serviceClass)
        }
    }
}