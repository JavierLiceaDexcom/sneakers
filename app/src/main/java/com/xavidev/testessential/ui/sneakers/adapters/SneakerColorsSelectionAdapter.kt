package com.xavidev.testessential.ui.sneakers.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.R
import com.xavidev.testessential.databinding.ItemSneakerColorSelectionBinding

typealias ColorClickListener = (String, Int) -> Unit

class SneakerColorsSelectionAdapter(private val itemClickListener: ColorClickListener) :
    ListAdapter<String, SneakerColorsSelectionAdapter.ViewHolder>(SneakerSizeSelectionDiffCallback()) {

    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    inner class ViewHolder(val binding: ItemSneakerColorSelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sneakerColor: String, itemClickListener: ColorClickListener) = with(binding) {
            itemSneakerColor.setOnClickListener {
                itemClickListener(sneakerColor, adapterPosition)
                selectedItemPos = adapterPosition
                lastItemSelectedPos = if (lastItemSelectedPos == -1)
                    selectedItemPos
                else {
                    notifyItemChanged(lastItemSelectedPos)
                    selectedItemPos
                }
                notifyItemChanged(selectedItemPos)
            }
            itemSneakerColor.setCardBackgroundColor(Color.parseColor(sneakerColor))
            executePendingBindings()
        }

        fun selectColor() {
            binding.itemSneakerColor.apply {
                strokeColor = binding.root.context.resources.getColor(R.color.primaryDark)
                strokeWidth = 8
            }
        }

        fun deselectColor() {
            binding.itemSneakerColor.apply {
                strokeColor = binding.root.context.resources.getColor(R.color.gray_darker)
                strokeWidth = 0
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSneakerColorSelectionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == selectedItemPos) holder.selectColor() else holder.deselectColor()
        holder.bind(currentList[position], itemClickListener)
    }
}

class SneakerSizeSelectionDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
}