package com.dtt.houseapp.data.API

import android.location.Location
import android.util.Log
import com.dtt.houseapp.data.HouseListRepositoryImpl
import com.dtt.houseapp.domain.HouseItem
import com.dtt.houseapp.utils.locationservice.LocationModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class ApiRequests{

    private val repository = HouseListRepositoryImpl


    fun getHouseList(locationObject: LocationModel){
        val results = FloatArray(1)
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
                        if(locationObject.latitude!=null && locationObject.longitude!=null){
                            Location.distanceBetween(locationObject.latitude!!, locationObject.longitude!!, responseArray[i].latitude.toDouble(), responseArray[i].longitude.toDouble(),results)
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
                                    results[0]/1000
                                )
                            )
                        }else{
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
                                    0f
                                )
                            )
                        }

                        Log.i("TestAPI", i.toString())
                    }
                }

                repository.setHouseList(houseItemsList)
            }

            override fun onFailure(call: Call<List<HouseAPIModel>>, t: Throwable) {

                Log.i("ApiResponse",t.message.toString())
            }

        })
    }
}