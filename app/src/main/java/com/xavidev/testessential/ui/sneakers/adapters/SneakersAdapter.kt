package com.xavidev.testessential.ui.sneakers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.source.local.entity.SneakerComplete
import com.xavidev.testessential.databinding.ItemSneakerGridBinding
import com.xavidev.testessential.SneakersApplication

typealias SneakerItemClickListener = (SneakerComplete, Int) -> Unit
typealias SneakerFavoriteClickListener = (SneakerComplete, Int) -> Unit

class SneakersAdapter(
    private val itemClickListener: SneakerItemClickListener,
    private val favoriteClickListener: SneakerFavoriteClickListener,
) :
    ListAdapter<SneakerComplete, SneakersAdapter.ViewHolder>(SneakersCallback) {

    inner class ViewHolder(val binding: ItemSneakerGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            sneakerItem: SneakerComplete,
            itemClickListener: SneakerItemClickListener,
            favoriteClickListener: SneakerFavoriteClickListener,
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
            loadColors(sneakerItem.colors, rvColors)
            executePendingBindings()
        }

        private fun loadColors(colors: List<String>, rvColors: RecyclerView) {
            val colorsAdapter = SneakerColorsAdapter()
            val linearLayoutManager =
                LinearLayoutManager(SneakersApplication.getContext(), LinearLayoutManager.HORIZONTAL, false)
            rvColors.apply {
                layoutManager = linearLayoutManager
                adapter = colorsAdapter
            }
            colorsAdapter.submitList(colors)
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
        holder.bind(currentList[position], itemClickListener, favoriteClickListener)
    }

    fun updateItem(sneakerItem: SneakerComplete, position: Int) {
        notifyItemChanged(position, sneakerItem)
    }
}