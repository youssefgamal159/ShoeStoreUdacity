package com.example.shoestore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoeViewModelFactory() : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ShoeViewModel::class.java)){
            return ShoeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}