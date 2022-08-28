package com.xavidev.testessential.ui.sneakers.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.databinding.ItemSneakerColorBinding

class SneakerColorsAdapter(private val itemClickListener: (Int, Int) -> Unit) :
    ListAdapter<Int, SneakerColorsAdapter.ViewHolder>(SneakerSizeCallback) {

    inner class ViewHolder(val binding: ItemSneakerColorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            sneakerColor: Int,
            itemClickListener: (Int, Int) -> Unit,
        ) = with(binding) {
            itemSneakerColor.setOnClickListener {
                itemClickListener(
                    sneakerColor,
                    this@ViewHolder.adapterPosition
                )
            }
            itemSneakerColor.setCardBackgroundColor(integerToRGB(sneakerColor).toArgb())
            executePendingBindings()
        }

        private fun integerToRGB(color: Int): Color {
            val strColor = color.toString()
            val red = strColor.substring(0, 2).toInt()
            val green = strColor.substring(3, 5).toInt()
            val blue = strColor.substring(6, 8).toInt()

            return if (strColor.length < 9) {
                Color.valueOf(Color.rgb(0, 0, 0))
            } else {
                Color.valueOf(Color.rgb(red, green, blue))
            }
        }
    }

    companion object {
        object SneakerSizeCallback : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem

            override fun areContentsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem
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