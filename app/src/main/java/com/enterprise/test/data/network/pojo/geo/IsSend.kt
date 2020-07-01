package com.enterprise.test.data.network.pojo.geo


import com.google.gson.annotations.SerializedName

data class IsSend(
    @SerializedName("error")
    val error: Any,
    @SerializedName("result")
    val result: Boolean
)