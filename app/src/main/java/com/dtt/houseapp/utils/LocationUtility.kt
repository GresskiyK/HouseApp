package com.dtt.houseapp.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dtt.houseapp.domain.HouseItem
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


object LocationUtility:LocationUtilityRepository {

    private val locationAvailability = MutableLiveData<Boolean>()
    private val locationObject= MutableLiveData<LocationModel>()


    fun hasLocationPermissions(activity:Activity){
        locationAvailability.postValue(ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
    }

    fun setLocation(view: Activity) {
        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(view)
        if (locationAvailability.value == true) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        updateLocationModel(LocationModel((location.latitude),(location.longitude)))
                    }
                }
        }else{
            updateStatus(false)
        }
    }

    override fun updateStatus(status: Boolean) {
        locationAvailability.postValue(status)
    }

    override fun updateLocationModel(locationModel: LocationModel) {
        locationObject.postValue(locationModel)
    }

    override fun getLocationObject(): LiveData<LocationModel> {
        return locationObject
    }


    override fun getStatus(): LiveData<Boolean> {
        return locationAvailability
    }
}
