package com.dtt.houseapp.data.API

import android.widget.ImageView
import com.google.gson.annotations.SerializedName

class HouseAPIModel {

    @SerializedName("id")
    var id : Int = 0
    @SerializedName("image")
    var image = "null"
    @SerializedName("price")
    var price: Int = 0
    @SerializedName("bedrooms")
    var bedroomAmount:Int = 0
    @SerializedName("bathrooms")
    var bathroomAmount:Int = 0
    @SerializedName("size")
    var size:Int = 0
    @SerializedName("description")
    var description = "null"
    @SerializedName("zip")
    var zip = "null"
    @SerializedName("city")
    var city = "null"
    @SerializedName("latitude")
    var latitude:Int = 0
    @SerializedName("longitude")
    var longitude:Int = 0
    @SerializedName("createdDate")
    var createdDate = "null"
}