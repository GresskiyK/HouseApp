package com.dtt.houseapp.data.API

import android.widget.ImageView
import com.dtt.houseapp.domain.HouseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @Headers(
        "Access-Key: 98bww4ezuzfePCYFxJEWyszbUXc7dxRx")
    @GET("api/house")
    fun getHouseList(): Call<List<HouseAPIModel>>
}