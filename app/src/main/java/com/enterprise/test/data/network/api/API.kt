package com.enterprise.test.data.network.api

import com.enterprise.test.data.network.NetworkService
import com.enterprise.test.data.network.pojo.driverinfo.DriverInfo
import com.enterprise.test.data.network.pojo.geo.Geo
import com.enterprise.test.data.network.pojo.geo.GeoItem
import com.enterprise.test.data.network.pojo.geo.GeoX
import com.enterprise.test.data.network.pojo.geo.IsSend
import com.enterprise.test.data.network.pojo.token.CreateToken
import com.enterprise.test.data.network.pojo.token.Token
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.Observer
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface API {

    @POST("driver/auth/auth")
    fun createToken(@Body deviceInfo: CreateToken): Observable<Token>

    @POST("locations/create")
    fun sendGeo(@Body geo: GeoX): Observable<IsSend>

    @POST("drivers")
    fun getDriverInfo(): Observable<DriverInfo>




    companion object FactoryPicture {
        fun retrofit(): API {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkService.BASE_URL)
                .build()

            return retrofit.create(API::class.java);
        }

        fun retrofitHeader(token: String): API {
            val okClient = OkHttpClient.Builder()
                .addInterceptor { chain ->

                    var request: Request = chain.request()
                    val url: HttpUrl =
                        request.url().newBuilder().build()
                    request = request.newBuilder().url(url).addHeader("Authorization", "Bearer $token").build()
                    chain.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okClient)
                .baseUrl(NetworkService.BASE_URL)
                .build()

            return retrofit.create(API::class.java)
        }
    }

}