package com.enterprise.test.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService private constructor() {
    lateinit var retrofit: Retrofit

    companion object {
        private var networkService: NetworkService? = null
        private const val BASE_URL = "https://testdriver.iwayex.com/v2/"
        val instance: NetworkService?
            get() {
                if (networkService == null) {
                    networkService = NetworkService()
                }
                return networkService
            }
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}