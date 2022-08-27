package com.xavidev.testessential.ui.sneakers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.databinding.ItemSneakerGridBinding

class SneakersAdapter(
    private val itemClickListener: (Sneaker, Int) -> Unit,
    private val favoriteClickListener: (Sneaker, Int) -> Unit,
    private val addClickListener: (Sneaker, Int) -> Unit,
) :
    ListAdapter<Sneaker, SneakersAdapter.ViewHolder>(sneakersCallback) {

    inner class ViewHolder(val binding: ItemSneakerGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            sneakerItem: Sneaker,
            itemClickListener: (Sneaker, Int) -> Unit,
            favoriteClickListener: (Sneaker, Int) -> Unit,
            addClickListener: (Sneaker, Int) -> Unit
        ) = with(binding) {
            sneaker = sneakerItem
            sneakerGridItem.setOnClickListener {  itemClickListener(sneakerItem, this@ViewHolder.adapterPosition) }
            imgAddFavorite.setOnClickListener { favoriteClickListener(sneakerItem, this@ViewHolder.adapterPosition) }
            imgAddCart.setOnClickListener { addClickListener(sneakerItem, this@ViewHolder.adapterPosition) }
            executePendingBindings()
        }
    }

    companion object {
        object sneakersCallback : DiffUtil.ItemCallback<Sneaker>() {
            override fun areItemsTheSame(oldItem: Sneaker, newItem: Sneaker) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Sneaker, newItem: Sneaker) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSneakerGridBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            currentList[position],
            itemClickListener,
            favoriteClickListener,
            addClickListener
        )
    }
}