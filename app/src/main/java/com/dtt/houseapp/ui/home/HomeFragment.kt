package com.dtt.houseapp.ui.home

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dtt.houseapp.R
import com.dtt.houseapp.databinding.ActivityMainScreenBinding
import com.dtt.houseapp.databinding.FragmentHomeBinding
import com.dtt.houseapp.presentation.HouseListAdapter
import com.dtt.houseapp.ui.houseDetailsScreen.HouseDetailsFragment
import com.dtt.houseapp.utils.CommunicatorForHouseDetailsScreen

/* This fragment is related to the screen with list of houses */


class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding: FragmentHomeBinding

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var houseListAdapter: HouseListAdapter
    private var rvLayoutManager: RecyclerView.LayoutManager? = null
    private lateinit var rvHouseRecycler: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var imageViewEmptySearch: ImageView
    private lateinit var inputMethodManager: InputMethodManager
    private lateinit var textViewEmpty: TextView
    private val communicatorForHouseDetailsScreenViewModel: CommunicatorForHouseDetailsScreen by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeFragmentBinding = FragmentHomeBinding.inflate(inflater)
        return homeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
        setRecycler()
        observeLocation()
        setSearchViewListener()
    }

    private fun initViews() {
        rvHouseRecycler = homeFragmentBinding.houseRecycler
        searchView = homeFragmentBinding.searchViewHouses
        imageViewEmptySearch = homeFragmentBinding.imageViewEmptySearch
        textViewEmpty = homeFragmentBinding.textViewEmpty
    }

    private fun setRecycler() {
        rvLayoutManager = LinearLayoutManager(activity)
        houseListAdapter = HouseListAdapter(this)
        with(rvHouseRecycler) {
            layoutManager = rvLayoutManager
            adapter = houseListAdapter
        }
    }

    private fun initViewModel() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private fun observeLocation() {
        homeViewModel.locationObject.observe(viewLifecycleOwner) {
            Log.i("LocationTester", it.latitude.toString())
            if (it.latitude != null) {
                homeViewModel.initHouseListWithLocationParam(it)
                observeViewModelSearch()
                houseItemClickListener()
            } else {
                homeViewModel.initHouseListWithLocationParam(it)
                observeViewModelSearch()
                houseItemClickListener()
            }
        }
    }

    private fun observeViewModelSearch() {
        homeViewModel.houseList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                imageViewEmptySearch.visibility = View.GONE
                textViewEmpty.visibility = View.GONE
                rvHouseRecycler.visibility = View.VISIBLE
                houseListAdapter.submitList(it)
            } else {
                rvHouseRecycler.visibility = View.GONE
                textViewEmpty.visibility = View.VISIBLE
                imageViewEmptySearch.visibility = View.VISIBLE
            }
        }
    }

    private fun setSearchViewListener() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                homeViewModel.receiveFilterQuery(query.toString())
                return false
            }
        })
    }

    private fun hideKeyboardIfNeeded() {
        inputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }


    private fun houseItemClickListener() {
        houseListAdapter.onHouseItemShortClickListener = {
            hideKeyboardIfNeeded()
            communicatorForHouseDetailsScreenViewModel.setHouseItem(it)
            val transaction = this.requireActivity().supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.slide_in_bottom,
                R.anim.slide_out_top,
                R.anim.slide_in_top,
                R.anim.slide_out_bottom
            )
            transaction.replace(this.id, HouseDetailsFragment())
            homeViewModel.setVisibilityOfBottomNavigation(false)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

}