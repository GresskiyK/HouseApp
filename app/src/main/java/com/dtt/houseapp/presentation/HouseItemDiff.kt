package com.dtt.houseapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.dtt.houseapp.domain.HouseItem

class HouseItemDiff: DiffUtil.ItemCallback<HouseItem>() {
    override fun areItemsTheSame(oldItem: HouseItem, newItem: HouseItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HouseItem, newItem: HouseItem): Boolean {
        return oldItem == newItem
    }
}