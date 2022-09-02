package com.xavidev.testessential.ui.sneakers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.entity.Brand
import com.xavidev.testessential.databinding.ItemBrandListBinding

class BrandsAdapter(
    private val itemClickListener: (Brand, Int) -> Unit
) :
    ListAdapter<Brand, BrandsAdapter.ViewHolder>(BrandsCallback) {

    inner class ViewHolder(val binding: ItemBrandListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            brandItem: Brand,
            itemClickListener: (Brand, Int) -> Unit,
        ) = with(binding) {
            brand = brandItem
            brandItemCard.setOnClickListener {
                itemClickListener(
                    brandItem,
                    this@ViewHolder.adapterPosition
                )
                cardContainer.isCheckable = true
                cardContainer.isChecked = brandItem.selected
            }
            executePendingBindings()
        }
    }

    companion object {
        object BrandsCallback : DiffUtil.ItemCallback<Brand>() {
            override fun areItemsTheSame(oldItem: Brand, newItem: Brand) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Brand, newItem: Brand) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBrandListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            currentList[position],
            itemClickListener,
        )
    }
}