package com.dtt.houseapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel(application: Application) : AndroidViewModel(application) {

    var liveData: MutableLiveData<Boolean> = MutableLiveData()

    fun initSplashScreen() {
        viewModelScope.launch {
            delay(2000)
            startShowMainScreen()
        }
    }

    private fun startShowMainScreen() {
        liveData.value = true
    }
}