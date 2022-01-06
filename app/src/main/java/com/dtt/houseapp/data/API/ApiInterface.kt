package com.dtt.houseapp.data.API

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

/* Interface is used for building retrofit object with
necessary extra fields including access-key */
interface ApiInterface {

    @Headers(
        "Access-Key: 98bww4ezuzfePCYFxJEWyszbUXc7dxRx"
    )
    @GET("api/house")
    suspend fun getHouseList(): Response<List<HouseAPIModel>>
}