package com.enterprise.test.presentation.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.enterprise.test.App
import com.enterprise.test.data.network.callback.TokenCallback
import com.enterprise.test.data.network.pojo.token.CreateToken
import com.enterprise.test.data.network.pojo.token.Token
import com.enterprise.test.domain.Repository

class MainViewModel() : ViewModel(), TokenCallback {
    private val repository = Repository()
    var context: Context? = null
    fun createToken(deviceInfo: CreateToken){
        repository.createToken(this, deviceInfo)
    }

    override fun onTokenCreated(token: Token) {
        if (token.error == null)
            App.instance!!.sharedPreferences!!.edit().putString("token", token.result.token).apply()
        else
            Toast.makeText(context, "${token.error} try later", Toast.LENGTH_LONG).show()
    }

    companion object{
        val TAG: String = "ViewModel"
    }
}