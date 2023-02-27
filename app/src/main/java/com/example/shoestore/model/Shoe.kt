package com.example.shoestore.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe (
    var name : String,
    var company : String,
    var size : String,
    var description : String,
    var imageShoe : Int
        ): Parcelable

data class ShoeImage(
    var imageModel : Int
//    val imagesShoes: MutableList<String> = mutableListOf("shoe1","shoe2","shoe3"
//        ,"shoe4","shoe5","shoe6","shoe7","shoe8","shoe9","shoe10")
)