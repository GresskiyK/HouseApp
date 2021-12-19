package com.dtt.houseapp.presentation.mainScreen

import androidx.lifecycle.ViewModel
import com.dtt.houseapp.data.HouseListRepositoryImpl
import com.dtt.houseapp.domain.GetHouseListUseCase
import com.dtt.houseapp.domain.GetLocationObject
import com.dtt.houseapp.domain.GetStatusOfLocation
import com.dtt.houseapp.utils.LocationUtility

class MainScreenViewModel:ViewModel() {

    private val repository = LocationUtility

}