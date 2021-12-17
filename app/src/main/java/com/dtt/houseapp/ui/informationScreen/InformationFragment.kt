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


class InformationFragment : Fragment() {

    private lateinit var informationViewModel: InformationViewModel
    private lateinit var tvLink:TextView

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_information,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initViewModel()
    }

    private fun initViews(view:View){
        tvLink = view.findViewById(R.id.textViewInformationDTTLink)
        tvLink.setOnClickListener{
            val url = "https://www.d-tt.nl/en/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
    private fun initViewModel(){
        informationViewModel =
            ViewModelProvider(this).get(InformationViewModel::class.java)
    }

}