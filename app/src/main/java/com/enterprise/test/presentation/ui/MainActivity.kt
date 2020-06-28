package com.enterprise.test.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.enterprise.test.App
import com.enterprise.test.R
import com.enterprise.test.data.network.manager.NetworkManager
import com.enterprise.test.data.network.pojo.geo.GeoItem
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
    private var deviceInfo: JSONObject? = null
    private val networkManager = NetworkManager()
    @SuppressLint("HardwareIds")

    private val os = "android"
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
        send = findViewById(R.id.send_geo)
        send.setOnClickListener { sendGeo(it);}

    }

    private fun sendGeo(view: View) {
        Toast.makeText(this, App.instance!!.sharedPreferences!!.getString("token",""), Toast.LENGTH_LONG).show()
        networkManager.sendGeo(App.instance!!.sharedPreferences!!.getString("token","")!!,GeoItem(Point(55.66969, 55.66969), "2020-06-28 06:38", 60, 2101015, 5))
    }



    companion object{
        val TAG: String = "MainActivity"
    }
}
