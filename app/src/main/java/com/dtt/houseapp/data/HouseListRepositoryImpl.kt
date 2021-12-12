package com.dtt.houseapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dtt.houseapp.data.API.ApiProtocol
import com.dtt.houseapp.data.API.ApiRequests
import com.dtt.houseapp.data.API.HouseAPIModel
import com.dtt.houseapp.domain.HouseItem
import com.dtt.houseapp.domain.HouseListRepository

object HouseListRepositoryImpl:HouseListRepository,ApiProtocol {

    private val houseListLiveData = MutableLiveData<List<HouseItem>>()

    private val houseListItems = sortedSetOf<HouseItem>({ p0, p1 -> p0.id.compareTo(p1.id) })



    init {
        ApiRequests().getHouseList()
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

    override fun getHouseList(list: List<HouseItem>) {
        for (item in list){
            addHouseItem(item)
            Log.i("TestApi", item.toString())

        }
    }
}