package com.xavidev.testessential.ui.sale.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.source.local.entity.SneakerComplete
import com.xavidev.testessential.databinding.ItemOrderItemBinding

class OrderReviewAdapter : ListAdapter<SneakerComplete, OrderReviewAdapter.ViewHolder>(OrderCallback) {

    inner class ViewHolder(private val binding: ItemOrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sneakerItem: SneakerComplete) = with(binding) {
            sneaker = sneakerItem
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    object OrderCallback : DiffUtil.ItemCallback<SneakerComplete>() {
        override fun areItemsTheSame(oldItem: SneakerComplete, newItem: SneakerComplete): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: SneakerComplete,
            newItem: SneakerComplete
        ): Boolean = oldItem == newItem
    }
}