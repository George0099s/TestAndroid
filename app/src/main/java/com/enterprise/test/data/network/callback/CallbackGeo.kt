package com.enterprise.test.data.network.callback

import com.enterprise.test.data.network.pojo.geo.IsSend

interface CallbackGeo {
    fun onDataLoaded(model: IsSend)
}