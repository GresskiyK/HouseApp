package com.dtt.houseapp.presentation.splashScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/* View model of SplashScreen activity */

class SplashScreenViewModel(application: Application) : AndroidViewModel(application) {

    var showingStatusForSplashScreen: MutableLiveData<Boolean> = MutableLiveData()

    //showing splash screen with delay
    fun initSplashScreen() {
        viewModelScope.launch {
            delay(2000)
            startShowMainScreen()
        }
    }

    private fun startShowMainScreen() {
        showingStatusForSplashScreen.value = true
    }
}