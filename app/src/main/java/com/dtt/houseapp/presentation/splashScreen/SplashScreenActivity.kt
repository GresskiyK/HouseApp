package com.dtt.houseapp.presentation.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dtt.houseapp.R
import com.dtt.houseapp.presentation.mainScreen.MainScreen

/* This class is used for making custom splash screen with DTT logo */


class SplashScreenActivity : AppCompatActivity() {
    private lateinit var splashViewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initViewModel()
        initSplashScreen()
        observeSplashLiveData()
    }


    private fun initViewModel() {
        splashViewModel = ViewModelProvider(this).get(SplashScreenViewModel::class.java)
    }

    private fun initSplashScreen() {
        splashViewModel.initSplashScreen()
    }

    private fun observeSplashLiveData() {
        splashViewModel.showingStatusForSplashScreen.observe(this) {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
}