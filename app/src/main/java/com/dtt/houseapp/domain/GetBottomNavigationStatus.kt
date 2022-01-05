package com.dtt.houseapp.domain

import androidx.lifecycle.MutableLiveData
import com.dtt.houseapp.utils.BottomNavigationRepository

class GetBottomNavigationStatus(private val repository: BottomNavigationRepository) {

    fun getBottomNavigationStatus(): MutableLiveData<Boolean> {
        return repository.getStatusOfBottomNavigation()
    }
}