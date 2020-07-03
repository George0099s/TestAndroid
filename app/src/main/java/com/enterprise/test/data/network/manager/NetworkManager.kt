package com.enterprise.test.data.network.manager

import com.enterprise.test.data.network.api.API
import com.enterprise.test.data.network.pojo.driverinfo.DriverInfo
import com.enterprise.test.data.network.pojo.geo.Geo
import com.enterprise.test.data.network.pojo.geo.GeoItem
import com.enterprise.test.data.network.pojo.geo.GeoX
import com.enterprise.test.data.network.pojo.geo.IsSend
import com.enterprise.test.data.network.pojo.token.CreateToken
import com.enterprise.test.data.network.pojo.token.Token
import io.reactivex.Observable


class NetworkManager {

    fun createToken(deviceInfo: CreateToken): Observable<Token> {
        return API.retrofit().createToken(deviceInfo)
    }

    fun sendGeo(token: String, geo: GeoX): Observable<IsSend> {
        return API.retrofitHeader(token).sendGeo(geo)
    }
    fun getDriverInfo(token: String): Observable<DriverInfo> {
        return API.retrofitHeader(token).getDriverInfo()
    }

    companion object{
        val TAG = "NetworkManager"
    }
}