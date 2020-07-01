package com.enterprise.test.presentation.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enterprise.test.App
import com.enterprise.test.data.network.callback.CallbackGeo
import com.enterprise.test.data.network.callback.TokenCallback
import com.enterprise.test.data.network.manager.NetworkManager
import com.enterprise.test.data.network.pojo.geo.GeoItem
import com.enterprise.test.data.network.pojo.geo.IsSend
import com.enterprise.test.data.network.pojo.geo.Point
import com.enterprise.test.data.network.pojo.token.CreateToken
import com.enterprise.test.data.network.pojo.token.Token
import com.enterprise.test.domain.Repository

class MainViewModel() : ViewModel(),CallbackGeo, TokenCallback {
    private val repository = Repository()
    var context: Context? = null
    val networkManager = NetworkManager()

    var geoLiveData: MutableLiveData<IsSend> = MutableLiveData()
    fun createToken(deviceInfo: CreateToken){
        repository.createToken(this, deviceInfo)
    }

    override fun onTokenCreated(token: Token) {
        if (token.error == null)
            App.instance!!.sharedPreferences!!.edit().putString("token", token.result.token).apply()
        else
            Toast.makeText(context, "${token.error} try later", Toast.LENGTH_LONG).show()
    }

    fun sendGeo() {
        networkManager.sendGeo(App.instance!!.sharedPreferences!!.getString("token",null)!!,
            GeoItem(Point(55.66969, 55.66969), "2020-06-28 06:38", 60, 2101015, 5)
        , this)
    }

    companion object{
        val TAG: String = "ViewModel"
    }

    override fun onDataLoaded(model: IsSend) {
        geoLiveData.postValue(model)
    }
}