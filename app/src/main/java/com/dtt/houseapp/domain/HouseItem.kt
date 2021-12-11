package com.dtt.houseapp.domain

data class HouseItem(
    val id:Int,
    val price:Int,
    val street:String,
    val size:Int,
    val bedroomAmount:Int,
    val bathroomAmount:Int,
    val distance:Float) {

}