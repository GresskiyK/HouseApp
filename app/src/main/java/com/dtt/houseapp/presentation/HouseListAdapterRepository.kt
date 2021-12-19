package com.dtt.houseapp.presentation

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.RequestBuilder

interface HouseListAdapterRepository {

    fun setImage(imageLink: String): RequestBuilder<Drawable>

    fun getFragmentContext():Fragment


}