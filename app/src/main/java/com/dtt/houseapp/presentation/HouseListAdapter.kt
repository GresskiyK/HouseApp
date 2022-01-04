package com.dtt.houseapp.presentation

import android.location.Location
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
import com.dtt.houseapp.domain.HouseItem
import com.dtt.houseapp.utils.locationservice.LocationModel


class HouseListAdapter(private val context:Fragment):ListAdapter<HouseItem,
        HouseListAdapter.HouseListViewHolder>(HouseItemDiff()) {


    var onHouseItemShortClickListener:((HouseItem)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseListViewHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.house_card,parent,false)
        return HouseListViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: HouseListViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvPrice.text = "$"+String.format("%,d", item.price)
        holder.tvStreet.text = item.zip.trim()+" "+item.city
        holder.tvBedroom.text = item.bedroomAmount.toString()
        holder.tvBathroom.text = item.bathroomAmount.toString()
        holder.tvSize.text = item.size.toString()
        Glide.with(context).load(item.imageLink).into(holder.ivHouse)
        holder.tvDistance.text = String.format("%.1f",item.distance) + " km"
        holder.itemView.setOnClickListener {
            onHouseItemShortClickListener?.invoke(item)
        }
    }

    class HouseListViewHolder(view: View):RecyclerView.ViewHolder(view){
        var ivHouse: ImageView = view.findViewById(R.id.imageViewHouse)
        var tvPrice: TextView = view.findViewById(R.id.tv_price)
        val tvStreet: TextView = view.findViewById(R.id.tv_street)
        val tvBedroom: TextView = view.findViewById(R.id.tv_bedroom)
        val tvBathroom: TextView = view.findViewById(R.id.tv_bathroom)
        val tvSize: TextView = view.findViewById(R.id.tv_size)
        val tvDistance: TextView = view.findViewById(R.id.tv_distance)



    }
}