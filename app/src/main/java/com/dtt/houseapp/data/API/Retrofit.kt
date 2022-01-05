package com.dtt.houseapp.data.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/* This class is used for building retrofit object with necessary settings */
class Retrofit {

    //function for building retrofit
    fun retrofitForHouseAPI(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl("https://intern.docker-dev.d-tt.nl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }
}