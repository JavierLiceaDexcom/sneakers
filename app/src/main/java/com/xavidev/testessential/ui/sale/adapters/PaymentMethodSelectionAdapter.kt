package com.xavidev.testessential.ui.sale.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.source.local.entity.Card
import com.xavidev.testessential.databinding.ItemPaymentMethodSelectionBinding
import kotlin.math.exp

typealias PaymentItemClickListener = (Card, Int) -> Unit

class PaymentMethodSelectionAdapter(private val itemListener: PaymentItemClickListener) :
    ListAdapter<Card, PaymentMethodSelectionAdapter.ViewHolder>(CardSelectionCallback) {

    inner class ViewHolder(private val binding: ItemPaymentMethodSelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cardItem: Card, itemListener: PaymentItemClickListener) = with(binding){
            card = cardItem
            executePendingBindings()
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
        val binding = ItemPaymentMethodSelectionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], itemListener)
    }
}