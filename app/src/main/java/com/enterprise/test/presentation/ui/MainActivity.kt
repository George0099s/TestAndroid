package com.enterprise.test.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.enterprise.test.App
import com.enterprise.test.R
import com.enterprise.test.data.network.manager.NetworkManager
import com.enterprise.test.data.network.pojo.driverinfo.DriverInfo
import com.enterprise.test.data.network.pojo.geo.GeoItem
import com.enterprise.test.data.network.pojo.geo.IsSend
import com.enterprise.test.data.network.pojo.geo.Point
import com.enterprise.test.data.network.pojo.token.DeviceInfo
import com.enterprise.test.data.network.pojo.token.CreateToken
import com.enterprise.test.presentation.viewmodel.MainViewModel
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private lateinit var send: Button
    private lateinit var viewModel: MainViewModel
    private val login = "917428730930"
    private val password = "351597"
    private val lat = 55.66969
    private val lon = 53.66969

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.context = this
        val createToken =
            CreateToken(
                DeviceInfo(
                    "123",
                    "eddd2222-wwww-www",
                    "android"
                ), login, password
            )
        viewModel.createToken(createToken)
        viewModel.geoLiveData.observe(this, isDataSend())
        viewModel.driverLiveData.observe(this, driverLiveData())
        send = findViewById(R.id.send_geo)
        send.setOnClickListener { sendGeo()}

    }

    private fun driverLiveData() = Observer<DriverInfo>{
        Log.d("222", it.result.lastLocation.lat.toString() + " " + it.result.lastLocation.lng)

        if (it.result.lastLocation.lat.equals(lat) && it.result.lastLocation.lng.equals(lon)) {
            Log.d("222", it.result.lastLocation.lat.toString() + " " + it.result.lastLocation.lng)
            Toast.makeText(this, "geo was saved", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "geo was not saved, try again", Toast.LENGTH_SHORT).show()
            sendGeo()
        }
    }

    private fun isDataSend() = Observer<IsSend>{
        if(it.result){
            viewModel.getDriverInfo()
        } else {
            Toast.makeText(this, "Something went", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendGeo() {
        viewModel.sendGeo(lat, lon)
    }



    companion object{
        val TAG: String = "MainActivity"
    }
}
