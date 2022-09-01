package com.xavidev.testessential.ui.sale.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.entity.Card
import com.xavidev.testessential.databinding.ItemAddressSelectionBinding

class PaymentMethodSelectionAdapter(private val itemListener: (Card, Int) -> Unit) :
    ListAdapter<Card, PaymentMethodSelectionAdapter.ViewHolder>(CardSelectionCallback) {

    inner class ViewHolder(binding: ItemAddressSelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cardItem: Card, itemListener: (Card, Int) -> Unit) {
            // Bind element
        }
    }

    companion object {
        object CardSelectionCallback : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(oldItem: Card, newItem: Card) =
                newItem.id == oldItem.id

            override fun areContentsTheSame(oldItem: Card, newItem: Card) = newItem == oldItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddressSelectionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], itemListener)
    }
}