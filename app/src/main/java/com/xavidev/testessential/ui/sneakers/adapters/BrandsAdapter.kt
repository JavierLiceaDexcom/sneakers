package com.xavidev.testessential.ui.sneakers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import com.xavidev.testessential.data.entity.Brand
import com.xavidev.testessential.databinding.ItemBrandListBinding

class BrandsAdapter(
    private val itemClickListener: (Brand, Int) -> Unit
) :
    ListAdapter<Brand, BrandsAdapter.ViewHolder>(brandsCallback) {

    inner class ViewHolder(val binding: ItemBrandListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            brandItem: Brand,
            itemClickListener: (Brand, Int) -> Unit,
        ) = with(binding) {
            brand = brandItem
            binding.brandItemCard.setOnClickListener {
                itemClickListener(
                    brandItem,
                    this@ViewHolder.adapterPosition
                )
                binding.cardContainer.isCheckable = true
                binding.cardContainer.isChecked = brandItem.selected
            }
            executePendingBindings()
        }
    }

    companion object {
        object brandsCallback : DiffUtil.ItemCallback<Brand>() {
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