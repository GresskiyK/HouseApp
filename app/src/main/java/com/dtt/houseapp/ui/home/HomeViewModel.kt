package com.dtt.houseapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dtt.houseapp.data.HouseListRepositoryImpl
import com.dtt.houseapp.domain.GetHouseListUseCase
import com.dtt.houseapp.domain.GetLocationObject
import com.dtt.houseapp.domain.GetStatusOfLocation
import com.dtt.houseapp.domain.SearchHouseItemCase
import com.dtt.houseapp.utils.LocationUtility

class HomeViewModel : ViewModel() {

    private val repository = HouseListRepositoryImpl

    private val locationRepository = LocationUtility

    private val getLocationObject = GetLocationObject(locationRepository)

    //insert implementation of getHouseListCase methods
    private val getHouseListCase = GetHouseListUseCase(repository)
    private val searchHouseItemCase = SearchHouseItemCase(repository)

    val locationObject = getLocationObject.getLocationObject()
    val houseList = getHouseListCase.getHouseList()

     fun receiveFilterQuery(query:String){
        searchHouseItemCase.searchHouse(query)
    }

}