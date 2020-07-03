package com.enterprise.test.domain
import com.enterprise.test.App
import com.enterprise.test.data.network.manager.NetworkManager
import com.enterprise.test.data.network.pojo.driverinfo.DriverInfo
import com.enterprise.test.data.network.pojo.geo.GeoX
import com.enterprise.test.data.network.pojo.geo.IsSend
import com.enterprise.test.data.network.pojo.token.CreateToken
import com.enterprise.test.data.network.pojo.token.Token
import io.reactivex.Observable

class Repository {
    private val networkManager = NetworkManager()

    fun createToken( deviceInfo: CreateToken): Observable<Token>{
      return networkManager.createToken(deviceInfo)
    }

    fun sendGeo(string: String, geo: GeoX): Observable<IsSend> {
       return networkManager.sendGeo(
            App.instance!!.sharedPreferences!!.getString("token", "")!!, geo)
    }

    fun getDriverInfo(string: String): Observable<DriverInfo> {
        return networkManager.getDriverInfo(App.instance!!.sharedPreferences!!.getString("token", "")!!)
    }


}