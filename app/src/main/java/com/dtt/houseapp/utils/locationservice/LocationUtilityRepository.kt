package com.dtt.houseapp.utils.locationservice

import androidx.lifecycle.LiveData


/* Interface is used for communicating with location utility */
interface LocationUtilityRepository {

    fun updateLocationModel(locationModel: LocationModel)
    fun getLocationObject(): LiveData<LocationModel>
}