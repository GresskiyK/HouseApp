package com.dtt.houseapp.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dtt.houseapp.domain.HouseItem

class Communicator: ViewModel() {

    val houseItem = MutableLiveData<HouseItem>()

    fun setHouseItem(item:HouseItem){
        houseItem.value = item
    }

}