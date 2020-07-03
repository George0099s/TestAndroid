package com.enterprise.test.data.network.pojo.driverinfo


import com.google.gson.annotations.SerializedName

data class LastLocation(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)