package com.dtt.houseapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dtt.houseapp.data.HouseListRepositoryImpl
import com.dtt.houseapp.domain.GetHouseListUseCase

class HomeViewModel : ViewModel() {

    private val repository = HouseListRepositoryImpl

    //insert implementation of getHouseListCase methods
    private val getHouseListCase = GetHouseListUseCase(repository)




    val houseList = getHouseListCase.getHouseList()


}