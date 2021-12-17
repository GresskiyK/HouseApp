package com.dtt.houseapp.ui.houseDetailsScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.dtt.houseapp.domain.HouseItem
import com.dtt.houseapp.utils.Communicator

import android.widget.ImageButton
import com.dtt.houseapp.R


class HouseDetailsFragment : Fragment() {

    private val communicatorViewModel: Communicator by activityViewModels()
    private lateinit var tvPrice: TextView
    private lateinit var tvBedroom: TextView
    private lateinit var tvBathroom: TextView
    private lateinit var tvSize: TextView
    private lateinit var tvDistance: TextView
    private lateinit var tvDescription: TextView
    private lateinit var imageViewHouseDetail: ImageView
    private lateinit var imageButtonBack:ImageButton





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_house_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        observeCommunicatorModel()
        setListenerForImageButton()
    }

    private fun setDataFromHouseItem(item:HouseItem){
        Glide.with(requireActivity()).load(item.imageLink).into(imageViewHouseDetail)
        tvPrice.text = "$"+String.format("%,d", item.price)
        tvBathroom.text = item.bathroomAmount.toString()
        tvBedroom.text = item.bedroomAmount.toString()
        tvSize.text = item.size.toString()
        tvDistance.text = "0"
        tvDescription.text = item.description
    }

    private fun observeCommunicatorModel(){
        communicatorViewModel.houseItem.observe(viewLifecycleOwner, {
            setDataFromHouseItem(it)
        })
    }

    private fun initViews(view:View){
        tvPrice = view.findViewById(R.id.tv_price)
        tvBedroom = view.findViewById(R.id.tv_bedroom)
        tvBathroom = view.findViewById(R.id.tv_bathroom)
        tvSize = view.findViewById(R.id.tv_size)
        tvDistance = view.findViewById(R.id.tv_distance)
        tvDescription = view.findViewById(R.id.tvDescription)
        imageViewHouseDetail = view.findViewById(R.id.imageViewHouseDetail)
        imageButtonBack = view.findViewById(R.id.imageButtonBack)
    }

    private fun setListenerForImageButton(){
        imageButtonBack.setOnClickListener {
                    val transaction = this.activity?.supportFragmentManager?.beginTransaction()
                    transaction?.setCustomAnimations(R.anim.slide_in_top,R.anim.slide_out_bottom)
                    transaction?.remove(this)
                    transaction?.commit()
            }
        }

}