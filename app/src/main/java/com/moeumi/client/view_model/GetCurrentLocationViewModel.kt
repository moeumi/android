package com.moeumi.client.view_model

import addressDummies
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.moeumi.client.data_type.Address
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class GetCurrentLocationViewModel : ViewModel() {
    private var _latitude = MutableStateFlow(0.0)
    val latitude = _latitude.asStateFlow()
    private var _longitude = MutableStateFlow(0.0)
    val longitude = _longitude.asStateFlow()

    private var _address = MutableStateFlow<Address>(addressDummies)

    //    private var _address = MutableStateFlow("")
    val address = _address.asStateFlow()

    private fun setLatitude(latitude: Double) {
        CoroutineScope(Dispatchers.Main).launch {
            _latitude.value = latitude
        }
    }

    private fun setLongitude(longitude: Double) {
        CoroutineScope(Dispatchers.Main).launch {
            _longitude.value = longitude
        }
    }

    private fun setAddress(address: Address) {
        CoroutineScope(Dispatchers.Main).launch {
            _address.value = address
        }
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        setLatitude(location.latitude)
                        setLongitude(location.longitude)
                    }
                }
            delay(1000)
        }
    }

    fun getCurrentAddress(context: Context) {
        getCurrentLocation(context = context)
        val lat = _latitude.value
        val long = _longitude.value
        val url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=$long&y=$lat"
        val gson = Gson()

        CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(url)
                .addHeader("Authorization", "KakaoAK ${"39b6c4f423c19eb1c773c4254ab40976"}")
                .build()

//            kotlin.runCatching {
            client.newCall(request).execute().also {
                when (it.isSuccessful) {
                    true -> {
                        val result = gson.fromJson(it.body!!.string(), Address::class.java)
                        setAddress(result)
                        Log.i("address_info", "url, $result")
                    }
                    else -> {
                        Log.i("address_info", "failed ${it.message}, $long $lat")
                    }
                }
            }
//            }
        }
    }
}
