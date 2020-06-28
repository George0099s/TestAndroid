package com.enterprise.test.domain

import com.enterprise.test.data.network.callback.TokenCallback
import com.enterprise.test.data.network.manager.NetworkManager
import com.enterprise.test.data.network.pojo.token.CreateToken

class Repository {
    private val networkManager = NetworkManager()


    fun createToken(callback: TokenCallback, deviceInfo: CreateToken){
      networkManager.createToken(callback, deviceInfo)
    }
}