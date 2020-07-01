package com.enterprise.test.data.network.manager

import android.util.Log
import com.enterprise.test.App
import com.enterprise.test.data.network.NetworkService
import com.enterprise.test.data.network.api.API
import com.enterprise.test.data.network.callback.CallbackGeo
import com.enterprise.test.data.network.callback.TokenCallback
import com.enterprise.test.data.network.pojo.geo.GeoItem
import com.enterprise.test.data.network.pojo.geo.IsSend
import com.enterprise.test.data.network.pojo.token.CreateToken
import com.enterprise.test.data.network.pojo.token.Token
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkManager {
//    private val token = App.sharedPreferences.getString("token", null)

    fun createToken(callback: TokenCallback, deviceInfo: CreateToken) {
        val retrofit: Retrofit = NetworkService.instance!!.retrofit

        val service: API = retrofit.create(API::class.java)
        val call: Call<Token> = service.createToken(deviceInfo)

        Log.d("$TAG Token", call.request().body().toString())
        call.enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>
            ) {
                Log.d("$TAG Token", response.body().toString())

                if (response.body() != null) {
                    val model: Token = response.body()!!
                      Log.d("$TAG Token", model.toString())
                    callback.onTokenCreated(model)
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.d("$TAG Token", t.message)
            }
        })
    }

    fun sendGeo(token: String, geo: GeoItem, callback: CallbackGeo) {
        val retrofit: Retrofit = NetworkService.instance!!.retrofit
        val okClient = OkHttpClient.Builder()
            .addInterceptor { chain ->

                var request: Request = chain.request()
                val url: HttpUrl =
                    request.url().newBuilder().build()
                request = request.newBuilder().url(url).addHeader("Authorization", "Bearer $token").build()
                chain.proceed(request)
            }
            .build()

        val service: API =
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkService.BASE_URL)
                .client(okClient)
                .build().create(API::class.java)
//        val service: API = retrofit.create(API::class.java)
        val call: Call<IsSend> = service.sendGeo(geo)

        call.enqueue(object : Callback<IsSend> {
            override fun onResponse(call: Call<IsSend>, response: Response<IsSend>
            ) {
                Log.d("$TAG sendGeo", response.toString())
                if (response.body() != null) {
                    val model: IsSend = response.body()!!
                    callback.onDataLoaded(model)
                }
            }

            override fun onFailure(call: Call<IsSend>, t: Throwable) {
                Log.d("$TAG sendGeo", t.message)
            }
        })
    }

    companion object{
        val TAG = "NetworkManager"
    }
}