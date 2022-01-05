package com.dtt.houseapp.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dtt.houseapp.domain.HouseItem

/* This class is used for sending clicked house object from fragment with list of houses
to fragment with detailed information for this house */

class CommunicatorForHouseDetailsScreen : ViewModel() {

    val houseItem = MutableLiveData<HouseItem>()

    //setting value to houseItem live data
    fun setHouseItem(item: HouseItem) {
        houseItem.value = item
    }


}