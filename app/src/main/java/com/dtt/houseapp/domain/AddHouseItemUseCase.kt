package com.dtt.houseapp.domain

class AddHouseItemUseCase(private val houseListRepository: HouseListRepository) {

    fun addHouseItem(houseItem: HouseItem){
        houseListRepository.addHouseItem(houseItem)
    }
}