package com.dtt.houseapp.utils

import androidx.lifecycle.MutableLiveData

interface BottomNavigationRepository {
    fun updateVisibilityOfBottomNavigation(status:Boolean)
    fun getStatusOfBottomNavigation(): MutableLiveData<Boolean>
}