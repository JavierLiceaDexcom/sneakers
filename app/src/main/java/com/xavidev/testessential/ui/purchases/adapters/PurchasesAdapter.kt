package com.xavidev.testessential.ui.purchases.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.entity.PurchaseItem
import com.xavidev.testessential.databinding.ItemPurchaseBinding

class PurchasesAdapter(
    private val itemClickListener: (PurchaseItem, Int) -> Unit,
    private val buyAgainClickListener: (PurchaseItem, Int) -> Unit,
) :
    ListAdapter<PurchaseItem, PurchasesAdapter.ViewHolder>(SneakerPurchaseCallback) {

    inner class ViewHolder(val binding: ItemPurchaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            purchaseItem: PurchaseItem,
            itemClickListener: (PurchaseItem, Int) -> Unit,
            buyAgainClickListener: (PurchaseItem, Int) -> Unit,
        ) = with(binding) {
            clPurchaseItem.setOnClickListener {  itemClickListener(purchaseItem, this@ViewHolder.adapterPosition) }
            btnBuyAgain.setOnClickListener { buyAgainClickListener(purchaseItem, this@ViewHolder.adapterPosition) }
            executePendingBindings()
        }
    }

    companion object {
        object SneakerPurchaseCallback : DiffUtil.ItemCallback<PurchaseItem>() {
            override fun areItemsTheSame(oldItem: PurchaseItem, newItem: PurchaseItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PurchaseItem, newItem: PurchaseItem) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPurchaseBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            currentList[position],
            itemClickListener,
            buyAgainClickListener,
        )
    }
}