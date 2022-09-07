package com.xavidev.testessential.ui.sneakers.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.databinding.ItemSneakerColorBinding
import com.xavidev.testessential.databinding.ItemSneakerColorSelectionBinding

class SneakerColorsAdapter :
    ListAdapter<String, SneakerColorsAdapter.ViewHolder>(SneakerSizeCallback) {

    inner class ViewHolder(val binding: ItemSneakerColorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sneakerColor: String) = with(binding) {
            itemSneakerColor.setCardBackgroundColor(Color.parseColor(sneakerColor))
            executePendingBindings()
        }
    }

    companion object {
        object SneakerSizeCallback : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bindingSimple = ItemSneakerColorBinding.inflate(inflater, parent, false)
        return ViewHolder(bindingSimple)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}