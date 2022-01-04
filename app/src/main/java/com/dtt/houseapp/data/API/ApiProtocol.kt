package com.dtt.houseapp.data.API

import com.dtt.houseapp.domain.HouseItem

interface ApiProtocol {

    fun setHouseList(list: List<HouseItem>){}
}