package com.dtt.houseapp.domain

import androidx.lifecycle.LiveData

class SearchHouseItemCase(private val houseListRepository: HouseListRepository) {

    fun searchHouse(query : String){
        return houseListRepository.searchHouse(query)
    }
}