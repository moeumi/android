package com.moeumi.client.view_model

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GetCurrentLocation : ViewModel() {
    private var _latitude = MutableStateFlow(0.0)
    val latitude = _latitude.asStateFlow()
    private var _longitude = MutableStateFlow(0.0)
    val longtitude = _longitude.asStateFlow()

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

    @SuppressLint("NotConstructor", "MissingPermission")
    fun getCurrentLocation(context: Context) {
        CoroutineScope(Dispatchers.IO).async {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        setLatitude(location.latitude)
                        setLongitude(location.longitude)
                    }
                }
        }.onAwait
    }
}
