package com.enterprise.test.data.network.callback

import com.enterprise.test.data.network.pojo.token.Token

interface TokenCallback {
    fun onTokenCreated(token: Token)

}