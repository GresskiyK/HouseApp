package com.dtt.houseapp.data.API

import com.dtt.houseapp.domain.HouseItem

/* Interface for ApiRequests class, in order to send successful response
to live data logic class(HouseListRepositoryImpl) */
interface ApiProtocol {
    fun setHouseList(list: Set<HouseItem>) {}
}