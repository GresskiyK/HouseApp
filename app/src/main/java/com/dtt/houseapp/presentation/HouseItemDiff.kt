package com.dtt.houseapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.dtt.houseapp.domain.HouseItem

/* This class is used in order to use the ListAdapter as root element for building the recyclerview list.
It's mandatory to add the class with DiffUtil functionality to List constructor,
so the List will place the elements according the specified field. */

class HouseItemDiff : DiffUtil.ItemCallback<HouseItem>() {
    override fun areItemsTheSame(oldItem: HouseItem, newItem: HouseItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HouseItem, newItem: HouseItem): Boolean {
        return oldItem == newItem
    }
}