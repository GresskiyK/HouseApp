package com.dtt.houseapp.domain

data class HouseItem(
    val id:Int,
    val imageLink:String,
    val price:Int,
    val bedroomAmount:Int,
    val bathroomAmount:Int,
    val size:Int,
    val description:String,
    val zip:String,
    val city:String,
    val latitude:Int,
    val longitude:Int,
    val createdDate:String,
    val distance:Float) {

}