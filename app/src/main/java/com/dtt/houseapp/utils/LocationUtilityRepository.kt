package com.dtt.houseapp.utils

import android.location.Location
import androidx.lifecycle.LiveData
import com.dtt.houseapp.domain.HouseItem

interface LocationUtilityRepository {

    fun updateLocationModel(locationModel:LocationModel)

    fun getLocationObject():LiveData<LocationModel>

    fun getStatus():LiveData<Boolean>

    fun updateLocationStatus(flag:Boolean)

}