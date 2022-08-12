package com.moeumi.client.view_model

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices
import com.moeumi.client.dummies.getCurrentDistrictUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class GetCurrentLocationViewModel : ViewModel() {
    private var _latitude = MutableStateFlow<Double>(0.0)
    val latitude = _latitude.asStateFlow()
    private var _longitude = MutableStateFlow<Double>(0.0)
    val longitude = _longitude.asStateFlow()

    private var _district = MutableStateFlow<String>("")
    val district = _district.asStateFlow()

    private fun setLatitude(latitude: Double) {
        CoroutineScope(Dispatchers.Main).launch {
            _latitude.value = latitude
        }.onJoin
    }

    private fun setLongitude(longitude: Double) {
        CoroutineScope(Dispatchers.Main).launch {
            _longitude.value = longitude
        }.onJoin
    }

    private fun setDistrict(district: String) {
        _district.value = district
    }

    @SuppressLint("MissingPermission")
    fun getCurrentDistrict(context: Context) {
        CoroutineScope(Dispatchers.IO).async {
            val url: String =
                "$getCurrentDistrictUrl?latitude=35.173095&longitude=129.130481"
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation
                .addOnSuccessListener { loca: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    if (loca != null) {
                        setLatitude(loca.latitude)
                        setLongitude(loca.longitude)
                        Log.d("location", "${loca.latitude}, ${loca.longitude}")
                    }
                }
            url
        }.onAwait
    }

    fun getDistrict(lat: String, long: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var url: String? = null
            if (lat != "0.0" && long != "0.0") {
                url =
                    "$getCurrentDistrictUrl?latitude=${lat}&longitude=${long}"
            }
            if (url != null) {
                val doc =
                    Jsoup.connect(url)
                        .get()
                val body = doc.select("body")
                val data = body.text()
                Log.d("data", data)
                setDistrict(
                    data
                )
            }
        }.onJoin
    }
}
