package com.example.shoestore


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.model.Shoe
import com.example.shoestore.model.ShoeImage

class ShoeViewModel () : ViewModel() {

    var shoe = Shoe("","","","", 0)
    var shoeImage = ShoeImage(0)

    private val itemsList: MutableLiveData<MutableList<ShoeImage>> = MutableLiveData()

    init {
        itemsList.value = setItems()
    }
    fun getListElements() : LiveData<MutableList<ShoeImage>>{
        return itemsList
    }

    private fun setItems() : MutableList<ShoeImage> {
        val itemArrayList:MutableList<ShoeImage> = ArrayList()
        itemArrayList.add(ShoeImage(R.drawable.shoe1))
        itemArrayList.add(ShoeImage(R.drawable.shoe2))
        itemArrayList.add(ShoeImage(R.drawable.shoe3))
        itemArrayList.add(ShoeImage(R.drawable.shoe4))
        itemArrayList.add(ShoeImage(R.drawable.shoe5))
        itemArrayList.add(ShoeImage(R.drawable.shoe6))
        itemArrayList.add(ShoeImage(R.drawable.shoe7))
        itemArrayList.add(ShoeImage(R.drawable.shoe8))
        itemArrayList.add(ShoeImage(R.drawable.shoe9))
        itemArrayList.add(ShoeImage(R.drawable.shoe10))
        itemArrayList.add(ShoeImage(R.drawable.shoe11))
        itemArrayList.add(ShoeImage(R.drawable.shoe12))
        itemArrayList.add(ShoeImage(R.drawable.shoe13))
        itemArrayList.add(ShoeImage(R.drawable.shoe14))
        itemArrayList.add(ShoeImage(R.drawable.shoe15))
        itemArrayList.add(ShoeImage(R.drawable.shoe16))
        itemArrayList.add(ShoeImage(R.drawable.shoe17))
        itemArrayList.add(ShoeImage(R.drawable.shoe18))
        itemArrayList.add(ShoeImage(R.drawable.shoe19))
        itemArrayList.add(ShoeImage(R.drawable.shoe20))
        return itemArrayList
    }
    private var _listShoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val listShoes: LiveData<MutableList<Shoe>>
        get() = _listShoes

    private val _eventSaveShoeDetailPress = MutableLiveData(false)
    val eventSaveShoeDetailPress: LiveData<Boolean>
        get() = _eventSaveShoeDetailPress

    private val _eventCancelShoeDetailPress = MutableLiveData(false)
    val eventCancelShoeDetailPress: LiveData<Boolean>
        get() = _eventCancelShoeDetailPress

    private val _eventSaveFailByNameShoeDetail = MutableLiveData(false)
    val eventSaveFailByNameShoeDetail: LiveData<Boolean>
        get() = _eventSaveFailByNameShoeDetail

    private val _eventSaveFailBySizeShoeDetail = MutableLiveData(false)
    val eventSaveFailBySizeShoeDetail: LiveData<Boolean>
        get() = _eventSaveFailBySizeShoeDetail

    private val _eventSaveFailByNameCompanyShoeDetail = MutableLiveData(false)
    val eventSaveFailByNameCompanyShoeDetail: LiveData<Boolean>
        get() = _eventSaveFailByNameCompanyShoeDetail

    fun saveShoeDetailStart() {
        when {
            shoe.company.trim().isEmpty() -> {
                _eventSaveFailByNameCompanyShoeDetail.value = true
            }
            shoe.name.trim().isEmpty() -> {
                _eventSaveFailByNameShoeDetail.value = true
            }
            shoe.size.trim().isEmpty() -> {
                _eventSaveFailBySizeShoeDetail.value = true
            }
            shoe.size.trim() < 36.toString()  -> {
                _eventSaveFailBySizeShoeDetail.value = true
            }
            shoe.size.trim() > 45.toString()  -> {
                _eventSaveFailBySizeShoeDetail.value = true
            }
            else -> {
                saveNewShoe()
                _eventSaveShoeDetailPress.value = true
            }
        }
    }
    fun saveShoeDetailComplete() {
        _eventSaveShoeDetailPress.value = false
    }
    fun saveFailByNameShoeDetailComplete() {
        _eventSaveFailByNameShoeDetail.value = false
    }
    fun saveFailByNameCompanyShoeDetailComplete() {
        _eventSaveFailByNameCompanyShoeDetail.value = false
    }
    fun saveFailBySizeShoeDetailComplete() {
        _eventSaveFailBySizeShoeDetail.value = false
    }
    fun cancelShoeDetailStart() {
        _eventCancelShoeDetailPress.value = true
    }
    fun cancelShoeDetailComplete() {
        _eventCancelShoeDetailPress.value = false
    }
    private fun saveNewShoe() {
        val list = _listShoes.value
        list?.let {
            it.add(shoe)
            _listShoes.setValue(list)
        }
    }
    fun clearShoeTemplate() {
        shoe = Shoe("", "", "", "", 0)
    }
}