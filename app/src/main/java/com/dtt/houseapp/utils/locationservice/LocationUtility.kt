package com.dtt.houseapp.utils.locationservice

import android.app.Activity
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


/* This class is related to sending location object to home fragment depending
on location availability from MainScreen class logic */

object LocationUtility: LocationUtilityRepository {

    private val locationAvailability = MutableLiveData<Boolean>()
    private val locationObject= MutableLiveData<LocationModel>()

    //setting value for locationAvailability live data
    fun setLocationStatus(flag:Boolean){
        locationAvailability.value = flag
    }

    //setting value for locationObject live data
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


    override fun updateLocationModel(locationModel: LocationModel) {
        locationObject.value = locationModel
    }

    override fun getLocationObject(): LiveData<LocationModel> {
        return locationObject
    }

}
