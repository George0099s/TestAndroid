package com.enterprise.test.data.network.pojo.geo


import com.google.gson.annotations.SerializedName

data class PointX(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)