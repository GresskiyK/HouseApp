package com.dtt.houseapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dtt.houseapp.R
import com.dtt.houseapp.databinding.FragmentHouseDetailsBinding
import com.dtt.houseapp.databinding.HouseCardBinding
import com.dtt.houseapp.domain.HouseItem


/* This class is used as adapter for recyclerview of houses */

class HouseListAdapter(private val context: Fragment) : ListAdapter<HouseItem,
        HouseListAdapter.HouseListViewHolder>(HouseItemDiff()) {

    private lateinit var houseCardBinding: HouseCardBinding
    var onHouseItemShortClickListener: ((HouseItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseListViewHolder {
        houseCardBinding = HouseCardBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.house_card, parent, false))
        return HouseListViewHolder(houseCardBinding)
    }

    override fun onBindViewHolder(holder: HouseListViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvPrice.text = "$" + String.format("%,d", item.price)
        holder.tvStreet.text = item.zip.trim() + " " + item.city
        holder.tvBedroom.text = item.bedroomAmount.toString()
        holder.tvBathroom.text = item.bathroomAmount.toString()
        holder.tvSize.text = item.size.toString()
        Glide.with(context).load(item.imageLink).into(holder.ivHouse)
        holder.tvDistance.text = String.format("%.1f", item.distance) + " km"
        holder.itemView.setOnClickListener {
            onHouseItemShortClickListener?.invoke(item)
        }
    }

    class HouseListViewHolder(_binding: HouseCardBinding) : RecyclerView.ViewHolder(_binding.root) {
        var ivHouse: ImageView = _binding.imageViewHouse
        var tvPrice: TextView = _binding.tvPrice
        val tvStreet: TextView = _binding.tvStreet
        val tvBedroom: TextView = _binding.tvBedroom
        val tvBathroom: TextView = _binding.tvBathroom
        val tvSize: TextView = _binding.tvSize
        val tvDistance: TextView = _binding.tvDistance
    }
}