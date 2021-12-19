package com.dtt.houseapp.data.API

import android.util.Log
import com.dtt.houseapp.data.HouseListRepositoryImpl
import com.dtt.houseapp.domain.HouseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class ApiRequests{

    private val repository = HouseListRepositoryImpl


    fun getHouseList(){
        val retrofit=Retrofit().retrofitForHouseAPI().getHouseList()
        retrofit.enqueue(object : Callback<List<HouseAPIModel>>{
            override fun onResponse(
                call: Call<List<HouseAPIModel>>,
                response: Response<List<HouseAPIModel>>
            ) {
                val responseArray = response.body()
                val houseItemsList = ArrayList<HouseItem>()
                if (responseArray != null){
                    for (i in 0 until (responseArray.size)) {
                        houseItemsList.add(
                            HouseItem(
                                responseArray[i].id,
                                "https://intern.docker-dev.d-tt.nl"+ responseArray[i].image,
                                responseArray[i].price,
                                responseArray[i].bedroomAmount,
                                responseArray[i].bathroomAmount,
                                responseArray[i].size,
                                responseArray[i].description,
                                responseArray[i].zip,
                                responseArray[i].city,
                                responseArray[i].latitude,
                                responseArray[i].longitude,
                                responseArray[i].createdDate,
                            )
                        )
                        Log.i("TestAPI", i.toString())
                    }
                }

                repository.getHouseList(houseItemsList)
            }

            override fun onFailure(call: Call<List<HouseAPIModel>>, t: Throwable) {

                Log.i("ApiResponse",t.message.toString())
            }

        })
    }
}