package com.enterprise.test.data.network.pojo.geo


import com.google.gson.annotations.SerializedName

data class Point(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)