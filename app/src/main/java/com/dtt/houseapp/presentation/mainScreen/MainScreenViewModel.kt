package com.dtt.houseapp.presentation.mainScreen

import androidx.lifecycle.ViewModel
import com.dtt.houseapp.data.HouseListRepositoryImpl
import com.dtt.houseapp.domain.GetBottomNavigationStatus
import com.dtt.houseapp.domain.GetHouseListUseCase
import com.dtt.houseapp.utils.BottomNavigationLogic
import com.dtt.houseapp.utils.BottomNavigationRepository
import com.dtt.houseapp.utils.locationservice.LocationUtility

class MainScreenViewModel:ViewModel() {

    private val repository = BottomNavigationLogic
    private val getStatusOfBottomNavigation = GetBottomNavigationStatus(repository)

    val bottomNavigationStatus = getStatusOfBottomNavigation.getBottomNavigationStatus()



}