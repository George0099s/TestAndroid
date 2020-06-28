package com.enterprise.test.data.network.pojo.token


import com.google.gson.annotations.SerializedName

data class CreateToken(
    @SerializedName("device_info")
    val deviceInfo: DeviceInfo,
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String
)