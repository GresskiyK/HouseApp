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
import com.dtt.houseapp.utils.locationservice.LocationUtility

/* This class is related to main activity of the app and contains all the logic where
activity context us needed and setting app bottom navigation with fragments  */

class MainScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding
    private lateinit var mainScreenViewModel: MainScreenViewModel
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        observeBottomNavigationVisibility()
        requestLocation()
    }

    private fun initBottomNavigationView() {
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main_screen)
        navView.setupWithNavController(navController)
    }

    private fun hideBottomNavigationView(){
        navView.visibility = View.GONE
    }

    private fun showBottomNavigationView(){
        navView.visibility = View.VISIBLE

    }

    private fun observeBottomNavigationVisibility(){
        mainScreenViewModel.bottomNavigationStatus.observe(this){
            if(it){
                showBottomNavigationView()
            }else{
                hideBottomNavigationView()
            }
        }
    }

    private fun initViewModel(){
        mainScreenViewModel =
            ViewModelProvider(this).get(MainScreenViewModel::class.java)
    }

    private fun requestLocation(){
        if(hasLocationPermissions()){
            LocationUtility.setLocationStatus(true)
            startFragments()
        }else{
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),0)
        }
    }

    private fun hasLocationPermissions():Boolean{
        return (ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun startFragments(){
        LocationUtility.setLocationObject(this)
        initBottomNavigationView()
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
                        .setPositiveButton("ОК") { _, _ ->
                            LocationUtility.setLocationStatus(false)
                            startFragments()}
                    alertDialog.create()
                    alertDialog.show()

                    Log.i("LocationRequest","location denied")
                }
                else if(grantResults.isNotEmpty() || grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.i("LocationRequest","location granted")
                    LocationUtility.setLocationStatus(true)
                    startFragments()
                }
            }
        }
    }
}