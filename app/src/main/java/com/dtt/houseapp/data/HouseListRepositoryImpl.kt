package com.dtt.houseapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dtt.houseapp.domain.HouseItem
import com.dtt.houseapp.domain.HouseListRepository
import kotlin.random.Random

object HouseListRepositoryImpl:HouseListRepository {

    private val houseListLiveData = MutableLiveData<List<HouseItem>>()

    private val houseListItems = sortedSetOf<HouseItem>({ p0, p1 -> p0.id.compareTo(p1.id) })



    init {
        for (i in 0 until 10){
            val item = HouseItem(i+1,100,"Street",50,3,4,54.5F)
            addHouseItem(item)
        }
    }

    private fun updateShopListLiveData(){
        houseListLiveData.postValue(houseListItems.toList())

    }

    override fun addHouseItem(houseItem: HouseItem) {
        houseListItems.add(houseItem)
        updateShopListLiveData()
    }

    override fun houseList(): LiveData<List<HouseItem>> {
        return houseListLiveData
    }
}