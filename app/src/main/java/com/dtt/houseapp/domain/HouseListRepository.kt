package com.dtt.houseapp.domain

import androidx.lifecycle.LiveData

interface HouseListRepository {
    fun houseList(): LiveData<List<HouseItem>>
    fun addHouseItem(houseItem: HouseItem)
    fun updateHouseListLiveData(houseList:Set<HouseItem>)
    fun searchHouse(query:String)
}