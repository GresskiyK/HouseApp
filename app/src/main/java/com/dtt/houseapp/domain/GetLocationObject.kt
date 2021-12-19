package com.dtt.houseapp.domain

import androidx.lifecycle.LiveData
import com.dtt.houseapp.utils.locationservice.LocationModel
import com.dtt.houseapp.utils.locationservice.LocationUtilityRepository

class GetLocationObject(private val locationUtilityRepository: LocationUtilityRepository) {

    fun getLocationObject(): LiveData<LocationModel> {
        return locationUtilityRepository.getLocationObject()
    }
}