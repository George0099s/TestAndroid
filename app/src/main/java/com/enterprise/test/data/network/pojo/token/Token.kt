package com.enterprise.test.data.network.pojo.token


import com.enterprise.test.data.network.pojo.token.Result
import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("error")
    val error: Any,
    @SerializedName("result")
    val result: Result
)