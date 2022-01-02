package com.dtt.houseapp.ui.home

import androidx.lifecycle.ViewModel
import com.dtt.houseapp.data.HouseListRepositoryImpl
import com.dtt.houseapp.domain.GetHouseListUseCase
import com.dtt.houseapp.domain.GetLocationObject
import com.dtt.houseapp.domain.SearchHouseItemCase
import com.dtt.houseapp.utils.BottomNavigationLogic
import com.dtt.houseapp.utils.BottomNavigationRepository
import com.dtt.houseapp.utils.locationservice.LocationUtility

class HomeViewModel : ViewModel() {

    private val houseListRepository = HouseListRepositoryImpl
    private val locationRepository = LocationUtility
    private val bottomNavigationRepository = BottomNavigationLogic

    private val getLocationObject = GetLocationObject(locationRepository)

    //insert implementation of getHouseListCase methods
    private val getHouseListCase = GetHouseListUseCase(houseListRepository)
    private val searchHouseItemCase = SearchHouseItemCase(houseListRepository)

    val locationObject = getLocationObject.getLocationObject()
    val houseList = getHouseListCase.getHouseList()

     fun receiveFilterQuery(query:String){
        searchHouseItemCase.searchHouse(query)
    }

    fun setVisibilityOfBottomNavigation(flag:Boolean){
        bottomNavigationRepository.updateVisibilityOfBottomNavigation(flag)
    }

}