package com.enterprise.test.data.network.manager

import android.util.Log
import com.enterprise.test.data.network.NetworkService
import com.enterprise.test.data.network.api.API
import com.enterprise.test.data.network.callback.TokenCallback
import com.enterprise.test.data.network.pojo.token.CreateToken
import com.enterprise.test.data.network.pojo.token.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class NetworkManager {
//    private val token = App.sharedPreferences.getString("token", null)

    fun createToken(callback: TokenCallback, deviceInfo: CreateToken) {
        val retrofit: Retrofit = NetworkService.instance!!.retrofit

        val service: API = retrofit.create(API::class.java)
        val call: Call<Token> = service.createToken(deviceInfo)

        Log.d("123", call.request().body().toString())
        call.enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>
            ) {
                Log.d(TAG, response.body().toString())

                if (response.body() != null) {
                    val model: Token = response.body()!!
                      Log.d(TAG, model.toString())
                    callback.onTokenCreated(model)
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.d(TAG, t.message)
            }
        })
    }

    companion object{
        val TAG = "NetworkManager"
    }
}