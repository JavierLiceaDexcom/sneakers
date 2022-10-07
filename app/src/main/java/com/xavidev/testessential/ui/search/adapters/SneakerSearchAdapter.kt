package com.xavidev.testessential.ui.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.source.local.entity.SneakerComplete
import com.xavidev.testessential.databinding.ItemSneakerSearchBinding
import com.xavidev.testessential.ui.sneakers.adapters.SneakerItemClickListener

class SneakerSearchAdapter(private val sneakerItemClickListener: SneakerItemClickListener) :
    ListAdapter<SneakerComplete, SneakerSearchAdapter.ViewHolder>(SearchSneakerCallback()) {

    inner class ViewHolder(private val binding: ItemSneakerSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sneakerItem: SneakerComplete, sneakerItemClickListener: SneakerItemClickListener) =
            with(binding) {
                sneaker = sneakerItem
                sneakerGridItem.setOnClickListener {
                    sneakerItemClickListener(sneakerItem, adapterPosition)
                }
                executePendingBindings()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSneakerSearchBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], sneakerItemClickListener)
    }
}

class SearchSneakerCallback : DiffUtil.ItemCallback<SneakerComplete>() {
    override fun areItemsTheSame(oldItem: SneakerComplete, newItem: SneakerComplete) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SneakerComplete, newItem: SneakerComplete) =
        oldItem == newItem
}