package com.dtt.houseapp.ui.houseDetailsScreen

import androidx.lifecycle.ViewModel
import com.dtt.houseapp.utils.BottomNavigationLogic

class HouseDetailsFragmentViewModel:ViewModel() {

    private val bottomNavigationRepository = BottomNavigationLogic

    fun setVisibilityOfBottomNavigation(flag:Boolean){
        bottomNavigationRepository.updateVisibilityOfBottomNavigation(flag)
    }
}