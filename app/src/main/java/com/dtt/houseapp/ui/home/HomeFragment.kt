package com.dtt.houseapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dtt.houseapp.R
import com.dtt.houseapp.presentation.HouseListAdapter
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var houseListAdapter:HouseListAdapter
    private var rvLayoutManager: RecyclerView.LayoutManager? = null
    private lateinit var rvHouseRecycler:RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var imageViewEmptySearch:ImageView




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
        observeViewModel()
        setSearchViewListener()

    }

    private fun initViews(view:View){
        rvHouseRecycler = view.findViewById(R.id.houseRecycler)
        searchView = view.findViewById(R.id.searchViewHouses)
        imageViewEmptySearch = view.findViewById(R.id.imageViewEmptySearch)
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
    }

    private fun observeViewModel(){
        homeViewModel.houseList.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                imageViewEmptySearch.visibility = View.GONE
                rvHouseRecycler.visibility = View.VISIBLE
                houseListAdapter.submitList(it)
            }
            else{
                rvHouseRecycler.visibility = View.GONE
                imageViewEmptySearch.visibility = View.VISIBLE
            }
        }
    }

    private fun setSearchViewListener(){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                homeViewModel.receiveFilterQuery(query.toString())
                return false
            }
        })
    }

}