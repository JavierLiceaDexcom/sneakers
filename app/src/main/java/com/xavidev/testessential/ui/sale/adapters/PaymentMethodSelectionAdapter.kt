package com.xavidev.testessential.ui.sale.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.source.local.entity.Card
import com.xavidev.testessential.databinding.ItemPaymentMethodSelectionBinding
import com.xavidev.testessential.utils.gone
import com.xavidev.testessential.utils.invisible
import com.xavidev.testessential.utils.showSnackbar
import com.xavidev.testessential.utils.visible
import kotlin.math.exp

typealias PaymentItemClickListener = (Card, Int) -> Unit

class PaymentMethodSelectionAdapter(private val itemListener: PaymentItemClickListener) :
    ListAdapter<Card, PaymentMethodSelectionAdapter.ViewHolder>(CardSelectionCallback) {
    var selectedItemPos = -1
    var lastItemSelectedPos = -1


    inner class ViewHolder(private val binding: ItemPaymentMethodSelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cardItem: Card, itemListener: PaymentItemClickListener) = with(binding) {
            card = cardItem
            clItemCard.setOnClickListener {
                itemListener(cardItem, adapterPosition)
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
            binding.imgSelected.visible()
        }

        fun uncheckCard() {
            binding.imgSelected.invisible()
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
        if (position == selectedItemPos) holder.checkCard() else holder.uncheckCard()
        holder.bind(currentList[position], itemListener)
    }
}