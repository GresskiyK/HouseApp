package com.dtt.houseapp.ui.houseDetailsScreen

import androidx.lifecycle.ViewModel
import com.dtt.houseapp.utils.BottomNavigationLogic

/* View model of HouseDetails fragment  */


class HouseDetailsFragmentViewModel : ViewModel() {

    private val bottomNavigationRepository = BottomNavigationLogic

    //changing visibility of bottom navigation
    fun setVisibilityOfBottomNavigation(flag: Boolean) {
        bottomNavigationRepository.updateVisibilityOfBottomNavigation(flag)
    }
}