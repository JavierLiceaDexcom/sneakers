package com.xavidev.testessential.ui.sneakers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.databinding.ItemSneakerSizesBinding

class SneakerSizesAdapter(private val itemClickListener: (Double, Int) -> Unit) :
    ListAdapter<Double, SneakerSizesAdapter.ViewHolder>(SneakerSizeCallback) {

    inner class ViewHolder(val binding: ItemSneakerSizesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            sneakerSize: Double,
            itemClickListener: (Double, Int) -> Unit,
        ) = with(binding) {
            size = sneakerSize
            sneakerSizeItem.setOnClickListener {
                itemClickListener(
                    sneakerSize,
                    this@ViewHolder.adapterPosition
                )
            }
             executePendingBindings()
        }
    }

    companion object {
        object SneakerSizeCallback : DiffUtil.ItemCallback<Double>() {
            override fun areItemsTheSame(oldItem: Double, newItem: Double) = oldItem == newItem

            override fun areContentsTheSame(oldItem: Double, newItem: Double) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSneakerSizesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            currentList[position],
            itemClickListener
        )
    }
}