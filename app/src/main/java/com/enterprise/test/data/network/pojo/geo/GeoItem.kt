package com.enterprise.test.data.network.pojo.geo


import com.google.gson.annotations.SerializedName

data class GeoItem(
    @SerializedName("point")
    val point: Point,
    @SerializedName("sent")
    val sent: String,
    @SerializedName("speed")
    val speed: Int,
    @SerializedName("trip_id")
    val tripId: Int,
    @SerializedName("type")
    val type: Int
)