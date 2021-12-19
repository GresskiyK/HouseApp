package com.dtt.houseapp.domain

import androidx.lifecycle.LiveData
import com.dtt.houseapp.utils.LocationUtilityRepository

class GetStatusOfLocation(private val locationUtilityRepository: LocationUtilityRepository) {

    fun geStatus(): LiveData<Boolean> {
        return locationUtilityRepository.getStatus()
    }
}