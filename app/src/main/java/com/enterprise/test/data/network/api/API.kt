package com.enterprise.test.data.network.api

import com.enterprise.test.data.network.pojo.geo.GeoItem
import com.enterprise.test.data.network.pojo.geo.IsSend
import com.enterprise.test.data.network.pojo.token.CreateToken
import com.enterprise.test.data.network.pojo.token.Token
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.Call
import retrofit2.http.*

interface API {

    @POST("driver/auth/auth")
    fun createToken(@Body deviceInfo: CreateToken): Call<Token>


    @POST("locations/create")
    fun sendGeo(@Body geo: GeoItem): Call<IsSend>
}