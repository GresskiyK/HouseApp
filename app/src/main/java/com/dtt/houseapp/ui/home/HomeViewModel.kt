package com.dtt.houseapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dtt.houseapp.data.HouseListRepositoryImpl
import com.dtt.houseapp.domain.GetHouseListUseCase
import com.dtt.houseapp.domain.GetLocationObject
import com.dtt.houseapp.domain.HouseItem
import com.dtt.houseapp.domain.SearchHouseItemCase
import com.dtt.houseapp.utils.BottomNavigationLogic
import com.dtt.houseapp.utils.locationservice.LocationModel
import com.dtt.houseapp.utils.locationservice.LocationUtility

class HomeViewModel : ViewModel() {

    private lateinit var houseListRepository: HouseListRepositoryImpl
    private val locationRepository = LocationUtility
    private val bottomNavigationRepository = BottomNavigationLogic

    private val getLocationObject = GetLocationObject(locationRepository)

    //insert implementation of getHouseListCase methods
    private lateinit var getHouseListCase:GetHouseListUseCase
    private lateinit var searchHouseItemCase:SearchHouseItemCase

    val locationObject = getLocationObject.getLocationObject()
    lateinit var houseList:LiveData<List<HouseItem>>

    fun initHouseListWithLocationParam(locationModel: LocationModel){
        HouseListRepositoryImpl.locationObject = locationModel
        houseListRepository = HouseListRepositoryImpl
        getHouseListCase = GetHouseListUseCase(houseListRepository)
        searchHouseItemCase = SearchHouseItemCase(houseListRepository)
        houseList = getHouseListCase.getHouseList()
    }


     fun receiveFilterQuery(query:String){
        searchHouseItemCase.searchHouse(query)
    }

    fun setVisibilityOfBottomNavigation(flag:Boolean){
        bottomNavigationRepository.updateVisibilityOfBottomNavigation(flag)
    }

}