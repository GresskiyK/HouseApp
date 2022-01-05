package com.dtt.houseapp.ui.houseDetailsScreen

import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.dtt.houseapp.domain.HouseItem
import com.dtt.houseapp.utils.CommunicatorForHouseDetailsScreen

import android.widget.ImageButton
import android.widget.ScrollView
import androidx.lifecycle.ViewModelProvider
import com.dtt.houseapp.R
import com.dtt.houseapp.ui.home.HomeViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL
import com.google.android.gms.maps.OnMapReadyCallback

import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/* This fragment is related to the screen with details of selected house */

class HouseDetailsFragment : Fragment(), OnMapReadyCallback {

    private val communicatorForHouseDetailsScreenViewModel: CommunicatorForHouseDetailsScreen by activityViewModels()
    private lateinit var houseDetailsFragmentViewModel: HouseDetailsFragmentViewModel
    private lateinit var tvPrice: TextView
    private lateinit var tvBedroom: TextView
    private lateinit var tvBathroom: TextView
    private lateinit var tvSize: TextView
    private lateinit var tvDistance: TextView
    private lateinit var tvDescription: TextView
    private lateinit var imageViewHouseDetail: ImageView
    private lateinit var imageButtonBack: ImageButton
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var mMap: GoogleMap
    private lateinit var scrollView: ScrollView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_house_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initViewModel()
        observeCommunicatorModel()
        setListenerForImageButton()
    }

    private fun setDataFromHouseItem(item: HouseItem) {
        Glide.with(requireActivity()).load(item.imageLink).into(imageViewHouseDetail)
        tvPrice.text = "$" + String.format("%,d", item.price)
        tvBathroom.text = item.bathroomAmount.toString()
        tvBedroom.text = item.bedroomAmount.toString()
        tvSize.text = item.size.toString()
        tvDistance.text = String.format("%.1f", item.distance) + " km"
        tvDescription.text = item.description
    }

    private fun observeCommunicatorModel() {
        communicatorForHouseDetailsScreenViewModel.houseItem.observe(viewLifecycleOwner, {
            setDataFromHouseItem(it)
        })
    }

    private fun initViews(view: View) {
        tvPrice = view.findViewById(R.id.tv_price)
        tvBedroom = view.findViewById(R.id.tv_bedroom)
        tvBathroom = view.findViewById(R.id.tv_bathroom)
        tvSize = view.findViewById(R.id.tv_size)
        tvDistance = view.findViewById(R.id.tv_distance)
        tvDescription = view.findViewById(R.id.tvDescription)
        imageViewHouseDetail = view.findViewById(R.id.imageViewHouseDetail)
        imageButtonBack = view.findViewById(R.id.imageButtonBack)
        scrollView = view.findViewById(R.id.scrollViewHouseContent)
        setTheMap()
    }

    private fun initViewModel() {
        houseDetailsFragmentViewModel =
            ViewModelProvider(this).get(HouseDetailsFragmentViewModel::class.java)
    }

    private fun setListenerForImageButton() {
        imageButtonBack.setOnClickListener {
            val transaction = this.activity?.supportFragmentManager?.beginTransaction()
            transaction?.setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom)
            transaction?.remove(this)
            transaction?.commit()
            this.onDestroyView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        houseDetailsFragmentViewModel.setVisibilityOfBottomNavigation(true)
    }

    private fun setTheMap() {
        mapFragment =
            childFragmentManager.findFragmentById(R.id.houseLocationMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        communicatorForHouseDetailsScreenViewModel.houseItem.observe(viewLifecycleOwner, { item ->
            mMap = map
            val house = LatLng(item.latitude.toDouble(), item.longitude.toDouble())
            val marker = mMap.addMarker(
                MarkerOptions()
                    .position(house)
                    .title("Marker on selected house")
                    .visible(true)
            )
            mMap.isBuildingsEnabled = true
            mMap.mapType = MAP_TYPE_NORMAL
            if (marker != null) {
                mMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        marker.position, 15.0f
                    )
                )
            }
            mMap.setOnCameraMoveStartedListener {
                when (it) {
                    GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE -> {
                        scrollView.requestDisallowInterceptTouchEvent(true);

                    }
                    GoogleMap.OnCameraMoveStartedListener
                        .REASON_API_ANIMATION -> {
                        scrollView.requestDisallowInterceptTouchEvent(true);

                    }
                    GoogleMap.OnCameraMoveStartedListener
                        .REASON_DEVELOPER_ANIMATION -> {
                        scrollView.requestDisallowInterceptTouchEvent(true);

                    }
                }
            }
        })
    }


}