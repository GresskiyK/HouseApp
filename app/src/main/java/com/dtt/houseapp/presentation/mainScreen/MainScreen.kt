package com.dtt.houseapp.presentation.mainScreen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dtt.houseapp.R
import com.dtt.houseapp.databinding.ActivityMainScreenBinding
import com.dtt.houseapp.ui.home.HomeViewModel
import com.dtt.houseapp.utils.LocationUtility


class MainScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding
    private lateinit var mainScreenViewModel: MainScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        observeViewModelLocationStatus()
        observeViewModelLocationCredentials()
        requestLocation()
    }

    private fun initBottomNavigationView() {
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main_screen)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController) }


    private fun initViewModel(){
        mainScreenViewModel =
            ViewModelProvider(this).get(MainScreenViewModel::class.java)
    }
    private fun requestLocation(){
        LocationUtility.hasLocationPermissions(this)
    }

    private fun observeViewModelLocationStatus(){
        mainScreenViewModel.status.observe(this){
            if(!it){
                ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),0
            )}
            else{
                //OPEN FRAGMENTS WITH MODE LOCATION ON
                LocationUtility.setLocation(this)
            }
        }
    }

    private fun observeViewModelLocationCredentials(){
        mainScreenViewModel.locationObject.observe(this){
            initBottomNavigationView()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            0 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    val alertDialog = AlertDialog.Builder(this)
                    alertDialog.setTitle("Notification")
                        .setMessage("Some functions related to usage of location could be unavailable.")
                        .setCancelable(false)
                        .setPositiveButton("ОК") { _, _ ->  initBottomNavigationView()}//OPEN FRAGMENTS WITH MODE LOCATION OFF

                    alertDialog.create()
                    alertDialog.show()
                    Log.i("LocationRequest","location denied")
                }
                else if(grantResults.isNotEmpty() || grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.i("LocationRequest","location granted")

                    //OPEN FRAGMENTS WITH MODE LOCATION ON
                    LocationUtility.setLocation(this)

                }
            }
        }
    }
}