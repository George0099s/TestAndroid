package com.enterprise.test.data.network.pojo.token


import com.google.gson.annotations.SerializedName

data class DeviceInfo(
    @SerializedName("app_version")
    val appVersion: String,
    @SerializedName("hardware_id")
    val hardwareId: String,
    @SerializedName("os")
    val os: String
)