package com.enterprise.test.data.network.api

import com.enterprise.test.data.network.pojo.geo.GeoItem
import com.enterprise.test.data.network.pojo.token.CreateToken
import com.enterprise.test.data.network.pojo.token.Token
import retrofit2.Call
import retrofit2.http.*

interface API {

    @POST("driver/auth/auth")
    fun createToken(@Body deviceInfo: CreateToken): Call<Token>


    @POST("locations/create")
    fun sendGeo(@Query("Authorization") token: String, @Body geo: GeoItem): Call<Token>
}