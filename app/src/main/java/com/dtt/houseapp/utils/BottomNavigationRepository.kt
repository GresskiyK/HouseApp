package com.dtt.houseapp.utils

import androidx.lifecycle.MutableLiveData

/* Interface in order to communicate with Bottom logic class */
interface BottomNavigationRepository {
    fun updateVisibilityOfBottomNavigation(status: Boolean)
    fun getStatusOfBottomNavigation(): MutableLiveData<Boolean>
}