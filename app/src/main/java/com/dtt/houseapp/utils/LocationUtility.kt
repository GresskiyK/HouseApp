package com.dtt.houseapp.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dtt.houseapp.data.API.ApiRequests
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


object LocationUtility:LocationUtilityRepository {

    private val locationAvailability = MutableLiveData<Boolean>()
    private val locationObject= MutableLiveData<LocationModel>()


    fun setLocationStatus(flag:Boolean){
        updateLocationStatus(flag)
    }

    fun setLocationObject(view: Activity) {
        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(view)
        if (locationAvailability.value == true) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        updateLocationModel(LocationModel((location.latitude),(location.longitude)))
                    }
                    else{
                        updateLocationModel(LocationModel(null,null))
                    }
                }
        }else{
            updateLocationModel(LocationModel(null,null))
        }
    }

    override fun updateLocationStatus(flag: Boolean) {
        locationAvailability.value = flag
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
