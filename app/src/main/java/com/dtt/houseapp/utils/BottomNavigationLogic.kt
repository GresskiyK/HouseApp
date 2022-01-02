package com.dtt.houseapp.utils

import androidx.lifecycle.MutableLiveData

object BottomNavigationLogic:BottomNavigationRepository {

    private val visibilityOfBottomNavigation = MutableLiveData<Boolean>()

    private fun updateVisibility(setting:Boolean){
        visibilityOfBottomNavigation.value = setting
    }

    override fun getStatusOfBottomNavigation(): MutableLiveData<Boolean> {
        return visibilityOfBottomNavigation
    }

    override fun updateVisibilityOfBottomNavigation(status:Boolean) {
        updateVisibility(status)
    }
}