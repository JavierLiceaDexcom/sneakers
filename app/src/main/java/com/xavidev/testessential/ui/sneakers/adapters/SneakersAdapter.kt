package com.xavidev.testessential.ui.sneakers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.entity.SneakerComplete
import com.xavidev.testessential.databinding.ItemSneakerGridBinding

class SneakersAdapter(
    private val itemClickListener: (SneakerComplete, Int) -> Unit,
    private val favoriteClickListener: (SneakerComplete, Int) -> Unit,
    private val addClickListener: (SneakerComplete, Int) -> Unit,
) :
    ListAdapter<SneakerComplete, SneakersAdapter.ViewHolder>(SneakersCallback) {

    inner class ViewHolder(val binding: ItemSneakerGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            sneakerItem: SneakerComplete,
            itemClickListener: (SneakerComplete, Int) -> Unit,
            favoriteClickListener: (SneakerComplete, Int) -> Unit,
            addClickListener: (SneakerComplete, Int) -> Unit
        ) = with(binding) {
            sneaker = sneakerItem
            sneakerGridItem.setOnClickListener {
                itemClickListener(
                    sneakerItem,
                    this@ViewHolder.adapterPosition
                )
            }
            imgAddFavorite.setOnClickListener {
                favoriteClickListener(
                    sneakerItem,
                    this@ViewHolder.adapterPosition
                )
            }
            imgAddCart.setOnClickListener {
                addClickListener(
                    sneakerItem,
                    this@ViewHolder.adapterPosition
                )
            }
            executePendingBindings()
        }
    }

    companion object {
        object SneakersCallback : DiffUtil.ItemCallback<SneakerComplete>() {
            override fun areItemsTheSame(oldItem: SneakerComplete, newItem: SneakerComplete) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: SneakerComplete, newItem: SneakerComplete) =
                oldItem == newItem
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

    fun updateItem(sneakerItem: SneakerComplete, position: Int) {
        notifyItemChanged(position, sneakerItem)
    }
}