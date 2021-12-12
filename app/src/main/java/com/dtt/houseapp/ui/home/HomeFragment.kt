package com.dtt.houseapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dtt.houseapp.R
import com.dtt.houseapp.databinding.FragmentHomeBinding
import com.dtt.houseapp.domain.HouseItem
import com.dtt.houseapp.presentation.HouseListAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var houseListAdapter:HouseListAdapter
    private var rvLayoutManager: RecyclerView.LayoutManager? = null
    private lateinit var rvHouseRecycler:RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setRecycler()
        initViewModel()

    }

    private fun initViews(view:View){
        rvHouseRecycler = view.findViewById<RecyclerView>(R.id.houseRecycler)
    }

    private fun setRecycler(){
        rvLayoutManager = LinearLayoutManager(activity)
        houseListAdapter= HouseListAdapter(this)
        with(rvHouseRecycler){
            layoutManager = rvLayoutManager
            adapter=houseListAdapter
        }

    }

    private fun initViewModel(){
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.houseList.observe(viewLifecycleOwner){
            houseListAdapter.submitList(it)
        }
    }
}