package com.dtt.houseapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dtt.houseapp.data.API.ApiProtocol
import com.dtt.houseapp.data.API.ApiRequests
import com.dtt.houseapp.domain.HouseItem
import com.dtt.houseapp.domain.HouseListRepository
import java.util.*

object HouseListRepositoryImpl:HouseListRepository,ApiProtocol {

    private val houseListLiveData = MutableLiveData<List<HouseItem>>()
    private val houseListItems = sortedSetOf<HouseItem>({ p0, p1 -> p0.price.compareTo(p1.price) })


    init {
        ApiRequests().getHouseList()
    }


    override fun updateHouseListLiveData(houseList:Set<HouseItem>){

        houseListLiveData.postValue(houseList.toList())
    }

    override fun addHouseItem(houseItem: HouseItem) {
        houseListItems.add(houseItem)
        updateHouseListLiveData(houseListItems)
    }

    override fun houseList(): LiveData<List<HouseItem>> {
        return houseListLiveData
    }

    override fun getHouseList(list: List<HouseItem>) {
        for (item in list){
            addHouseItem(item)
        }
    }

    override fun searchHouse(query: String) {
        val filteredHouses = sortedSetOf<HouseItem>({ p0, p1 -> p0.price.compareTo(p1.price) })
        for (item in houseListItems){
            if (item.zip.lowercase(Locale.getDefault()).contains(query.lowercase(Locale.getDefault()))){
                filteredHouses.add(item)
            }
        }
        updateHouseListLiveData(filteredHouses)
    }
}