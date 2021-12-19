package com.dtt.houseapp.utils.locationservice

import androidx.lifecycle.LiveData

interface LocationUtilityRepository {

    fun updateLocationModel(locationModel: LocationModel)

    fun getLocationObject():LiveData<LocationModel>

    fun updateLocationStatus(flag:Boolean)

}