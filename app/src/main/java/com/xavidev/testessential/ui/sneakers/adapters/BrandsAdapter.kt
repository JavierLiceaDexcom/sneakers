package com.xavidev.testessential.ui.sneakers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.source.local.entity.Brand
import com.xavidev.testessential.databinding.ItemBrandListBinding

typealias BrandItemClickListener = (Brand, Int) -> Unit

class BrandsAdapter(private val itemClickListener: BrandItemClickListener) :
    ListAdapter<Brand, BrandsAdapter.ViewHolder>(BrandsCallback) {

    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    inner class ViewHolder(val binding: ItemBrandListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            brandItem: Brand,
            itemClickListener: BrandItemClickListener,
        ) = with(binding) {
            brand = brandItem
            clItemCard.setOnClickListener {
                itemClickListener(brandItem, adapterPosition)
                selectedItemPos = adapterPosition
                lastItemSelectedPos = if (lastItemSelectedPos == -1)
                    selectedItemPos
                else {
                    notifyItemChanged(lastItemSelectedPos)
                    selectedItemPos
                }
                notifyItemChanged(selectedItemPos)
            }
            executePendingBindings()
        }

        fun checkCard() {
            binding.cardContainer.isChecked = true
        }

        fun uncheckCard() {
            binding.cardContainer.isChecked = false
        }
    }

    companion object {
        object BrandsCallback : DiffUtil.ItemCallback<Brand>() {
            override fun areItemsTheSame(oldItem: Brand, newItem: Brand) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Brand, newItem: Brand) = oldItem == newItem
        }
    }

    fun clearSelectedItem() {
        notifyItemChanged(lastItemSelectedPos)
        selectedItemPos = -1
        lastItemSelectedPos = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBrandListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == selectedItemPos) holder.checkCard() else holder.uncheckCard()
        holder.bind(currentList[position], itemClickListener)
    }
}