package com.dtt.houseapp.ui.informationScreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dtt.houseapp.R
import com.dtt.houseapp.databinding.FragmentHouseDetailsBinding
import com.dtt.houseapp.databinding.FragmentInformationBinding

/* This fragment is related to the screen with information about project and DTT link */


class InformationFragment : Fragment() {


    private lateinit var informationFragmentBinding: FragmentInformationBinding

    private lateinit var informationViewModel: InformationViewModel
    private lateinit var tvLink: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        informationFragmentBinding = FragmentInformationBinding.inflate(inflater)
        return informationFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setClickListenerForDttLink()
        initViewModel()
    }

    private fun initViews() {
        tvLink = informationFragmentBinding.textViewInformationDTTLink
    }

    private fun setClickListenerForDttLink(){
        tvLink.setOnClickListener {
            val url = "https://www.d-tt.nl/en/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    private fun initViewModel() {
        informationViewModel =
            ViewModelProvider(this).get(InformationViewModel::class.java)
    }

}