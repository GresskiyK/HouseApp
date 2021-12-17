package com.dtt.houseapp.ui.informationScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InformationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is informationScreen Fragment"
    }
    val text: LiveData<String> = _text
}