package com.xavidev.testessential.ui.purchases.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.source.local.entity.Cart
import com.xavidev.testessential.databinding.ItemPurchaseBinding

class PurchasesAdapter(
    private val itemClickListener: (Cart, Int) -> Unit,
    private val buyAgainClickListener: (Cart, Int) -> Unit,
) :
    ListAdapter<Cart, PurchasesAdapter.ViewHolder>(SneakerPurchaseCallback) {

    inner class ViewHolder(val binding: ItemPurchaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            cart: Cart,
            itemClickListener: (Cart, Int) -> Unit,
            buyAgainClickListener: (Cart, Int) -> Unit,
        ) = with(binding) {
            clPurchaseItem.setOnClickListener {  itemClickListener(cart, this@ViewHolder.adapterPosition) }
            btnBuyAgain.setOnClickListener { buyAgainClickListener(cart, this@ViewHolder.adapterPosition) }
            executePendingBindings()
        }
    }

    companion object {
        object SneakerPurchaseCallback : DiffUtil.ItemCallback<Cart>() {
            override fun areItemsTheSame(oldItem: Cart, newItem: Cart) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cart, newItem: Cart) = oldItem == newItem
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