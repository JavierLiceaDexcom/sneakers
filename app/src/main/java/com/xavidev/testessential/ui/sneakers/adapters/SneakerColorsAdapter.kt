package com.xavidev.testessential.ui.sneakers.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.databinding.ItemSneakerColorBinding

class SneakerColorsAdapter(private val itemClickListener: (String, Int) -> Unit) :
    ListAdapter<String, SneakerColorsAdapter.ViewHolder>(SneakerSizeCallback) {

    inner class ViewHolder(val binding: ItemSneakerColorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            sneakerColor: String,
            itemClickListener: (String, Int) -> Unit,
        ) = with(binding) {
            itemSneakerColor.setOnClickListener {
                itemClickListener(
                    sneakerColor,
                    this@ViewHolder.adapterPosition
                )
            }
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
        val binding = ItemSneakerColorBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            currentList[position],
            itemClickListener
        )
    }
}