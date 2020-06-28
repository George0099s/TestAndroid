package com.enterprise.test.data.network.pojo.token


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("token")
    val token: String
)