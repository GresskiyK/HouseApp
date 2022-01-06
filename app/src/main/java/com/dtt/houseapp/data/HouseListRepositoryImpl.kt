package com.dtt.houseapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dtt.houseapp.data.API.ApiProtocol
import com.dtt.houseapp.data.API.ApiRequests
import com.dtt.houseapp.domain.HouseItem
import com.dtt.houseapp.domain.HouseListRepository
import com.dtt.houseapp.utils.locationservice.LocationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/* This class contains all the logic for working with live data house list and search option*/
object HouseListRepositoryImpl : HouseListRepository, ApiProtocol {

    private val houseListLiveData = MutableLiveData<List<HouseItem>>()

    //list is used for saving all the cached houses, in order to use search option with zip code
    private lateinit var fullHouseList: Set<HouseItem>

    var locationObject = LocationModel(null, null)
        set(value) {
            CoroutineScope(Dispatchers.IO).launch {
                val result = ApiRequests().getHouseList(value)
                bindDataWithUi(result)
            }
        }

    private suspend fun bindDataWithUi(houses:Set<HouseItem>){
        withContext(Dispatchers.Main){
            setHouseList(houses)
        }
    }
    //updating live data of houses list
    override fun updateHouseListLiveData(houseList: Set<HouseItem>) {
        houseListLiveData.postValue(houseList.toList())
    }

    //getting house list from api
    override fun setHouseList(list: Set<HouseItem>) {
        fullHouseList = list
        updateHouseListLiveData(fullHouseList)
    }

    //getting house list live data
    override fun houseList(): LiveData<List<HouseItem>> {
        return houseListLiveData
    }

    //searching compliant houses with query
    override fun searchHouse(query: String) {
        val filteredHouses = sortedSetOf<HouseItem>({ p0, p1 -> p0.price.compareTo(p1.price) })
        for (item in fullHouseList) {
            if (item.zip.lowercase(Locale.getDefault())
                    .contains(query.lowercase(Locale.getDefault()))
            ) {
                filteredHouses.add(item)
            }
        }
        updateHouseListLiveData(filteredHouses)
    }
}