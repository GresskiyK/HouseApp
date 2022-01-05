package com.dtt.houseapp.domain

import androidx.lifecycle.LiveData

/* Interface is used for making connection between viewmodels and main repository */

interface HouseListRepository {
    fun houseList(): LiveData<List<HouseItem>>
    fun updateHouseListLiveData(houseList: Set<HouseItem>)
    fun searchHouse(query: String)
}