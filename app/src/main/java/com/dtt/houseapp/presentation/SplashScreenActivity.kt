package com.dtt.houseapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dtt.houseapp.R
import com.dtt.houseapp.domain.SplashModel

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var splashViewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initViewModel()
        observeSplashLiveData()
    }



    private fun initViewModel() {
        splashViewModel = ViewModelProvider(this).get(SplashScreenViewModel::class.java)
    }

    private fun observeSplashLiveData() {
        splashViewModel.initSplashScreen()
        val observer = Observer<SplashModel> {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        splashViewModel.liveData.observe(this, observer)
    }
}