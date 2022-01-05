package com.dtt.houseapp.domain

import androidx.lifecycle.LiveData

class GetHouseListUseCase(private val houseListRepository: HouseListRepository) {

    fun getHouseList(): LiveData<List<HouseItem>> {
        return houseListRepository.houseList()
    }


}