package com.enterprise.test.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enterprise.test.App
import com.enterprise.test.data.network.manager.NetworkManager
import com.enterprise.test.data.network.pojo.driverinfo.DriverInfo
import com.enterprise.test.data.network.pojo.geo.GeoItemX
import com.enterprise.test.data.network.pojo.geo.GeoX
import com.enterprise.test.data.network.pojo.geo.IsSend
import com.enterprise.test.data.network.pojo.geo.PointX
import com.enterprise.test.data.network.pojo.token.CreateToken
import com.enterprise.test.domain.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel() : ViewModel() {
    private val repository = Repository()
    var context: Context? = null
    var geoLiveData: MutableLiveData<IsSend> = MutableLiveData()
    var driverLiveData: MutableLiveData<DriverInfo> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun createToken(deviceInfo: CreateToken) {
        repository.createToken(deviceInfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.error == null)
                    App.instance!!.sharedPreferences!!.edit().putString("token", it.result.token)
                        .apply()
                else
                    Toast.makeText(context, "${it.error} try later", Toast.LENGTH_LONG).show()
            }, {
                Log.d("233", it.localizedMessage)

            })
    }


    @SuppressLint("CheckResult")
    fun sendGeo(lat: Double, lon: Double) {

        val point = PointX(lat, lon)
        val item = GeoItemX(point, "2020-07-03 12:44:12", 60, 2101015, 1)
        var geo = GeoX()
        geo.add(item)
        repository.sendGeo(App.instance!!.sharedPreferences!!.getString("token", "")!!, geo).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                geoLiveData.postValue(it)
            }, {
                Log.d("233", it.localizedMessage)
            })


    }


    @SuppressLint("CheckResult")
    fun getDriverInfo() {
        repository.getDriverInfo(App.instance!!.sharedPreferences!!.getString("token", "")!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                driverLiveData.postValue(it)
            },{
                Log.d("233", it.localizedMessage)
            })
    }

    companion object {
        val TAG: String = "ViewModel"
    }

}