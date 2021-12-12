package com.dtt.houseapp.data.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    fun retrofitForHouseAPI(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl("https://intern.docker-dev.d-tt.nl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }
}