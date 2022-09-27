package com.xavidev.testessential.ui.paymentMethods.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.source.local.entity.Card
import com.xavidev.testessential.databinding.ItemCardBinding

typealias OnCardItemClick = (String) -> Unit
typealias OnCardRemoveClick = (String) -> Unit

class CardsAdapter(
    private val cardItemListener: OnCardItemClick,
    private val cardRemoveListener: OnCardRemoveClick
) : ListAdapter<Card, CardsAdapter.ViewHolder>(CardDiffUtil()) {

    inner class ViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            cardItem: Card,
            cardItemClick: OnCardItemClick,
            cardRemoveClick: OnCardRemoveClick
        ) = with(binding) {
            card = cardItem
            clItemCard.setOnClickListener { cardItemClick(cardItem.id) }
            imgRemoveCard.setOnClickListener { cardRemoveClick(cardItem.id) }
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], cardItemListener, cardRemoveListener)
    }
}

class CardDiffUtil : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Card, newItem: Card) = oldItem == newItem
}