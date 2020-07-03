package com.enterprise.test.data.network.pojo.driverinfo


import com.google.gson.annotations.SerializedName

data class DriverInfo(
    @SerializedName("error")
    val error: Any,
    @SerializedName("result")
    val result: Result
)