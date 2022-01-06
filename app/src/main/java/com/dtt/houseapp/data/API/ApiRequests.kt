package com.dtt.houseapp.data.API

import android.location.Location
import android.util.Log
import com.dtt.houseapp.data.HouseListRepositoryImpl
import com.dtt.houseapp.domain.HouseItem
import com.dtt.houseapp.utils.locationservice.LocationModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.ArrayList

/* This class is used for sending request to get the full house list from API */
class ApiRequests{

    private val repository = HouseListRepositoryImpl


    //function for sending request to API
    suspend fun getHouseList(locationObject: LocationModel):Set<HouseItem>{
        val results = FloatArray(1)
        val sortedHouseList = sortedSetOf<HouseItem>({ p0, p1 -> p0.price.compareTo(p1.price) })
        val response = Retrofit().retrofitForHouseAPI().getHouseList()
        if (response.isSuccessful && response.body()!!.isNotEmpty()) {
            for (i in 0 until (response.body()!!.size)) {
                if (locationObject.latitude != null && locationObject.longitude != null) {
                    Location.distanceBetween(
                        locationObject.latitude!!,
                        locationObject.longitude!!,
                        response.body()!![i].latitude.toDouble(),
                        response.body()!![i].longitude.toDouble(),
                        results
                    )
                    sortedHouseList.add(
                        HouseItem(
                            response.body()!![i].id,
                            "https://intern.docker-dev.d-tt.nl" + response.body()!![i].image,
                            response.body()!![i].price,
                            response.body()!![i].bedroomAmount,
                            response.body()!![i].bathroomAmount,
                            response.body()!![i].size,
                            response.body()!![i].description,
                            response.body()!![i].zip,
                            response.body()!![i].city,
                            response.body()!![i].latitude,
                            response.body()!![i].longitude,
                            response.body()!![i].createdDate,
                            results[0] / 1000
                        )
                    )
                } else {
                    sortedHouseList.add(
                        HouseItem(
                            response.body()!![i].id,
                            "https://intern.docker-dev.d-tt.nl" + response.body()!![i].image,
                            response.body()!![i].price,
                            response.body()!![i].bedroomAmount,
                            response.body()!![i].bathroomAmount,
                            response.body()!![i].size,
                            response.body()!![i].description,
                            response.body()!![i].zip,
                            response.body()!![i].city,
                            response.body()!![i].latitude,
                            response.body()!![i].longitude,
                            response.body()!![i].createdDate,
                            0f
                        )
                    )
                }
            }
            return sortedHouseList
        } else {
            Log.i("TestAPI", "Error loading")
            return sortedHouseList
        }
    }
}