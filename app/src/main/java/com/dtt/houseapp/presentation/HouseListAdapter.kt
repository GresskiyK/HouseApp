package com.dtt.houseapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dtt.houseapp.R
import com.dtt.houseapp.domain.HouseItem

class HouseListAdapter:ListAdapter<HouseItem,HouseListAdapter.HouseListViewHolder>(HouseItemDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseListViewHolder {

        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.house_card,parent,false)
        return HouseListViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: HouseListViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvPrice.text = item.price.toString()
        holder.tvStreet.text = item.street
        holder.tvBedroom.text = item.bedroomAmount.toString()
        holder.tvBathroom.text = item.bathroomAmount.toString()
        holder.tvSize.text = item.size.toString()
        holder.tvDistance.text = item.distance.toString()

    }

    class HouseListViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvPrice: TextView = view.findViewById(R.id.tv_price)
        val tvStreet: TextView = view.findViewById(R.id.tv_street)
        val tvBedroom: TextView = view.findViewById(R.id.tv_bedroom)
        val tvBathroom: TextView = view.findViewById(R.id.tv_bathroom)
        val tvSize: TextView = view.findViewById(R.id.tv_size)
        val tvDistance: TextView = view.findViewById(R.id.tv_distance)
    }
}