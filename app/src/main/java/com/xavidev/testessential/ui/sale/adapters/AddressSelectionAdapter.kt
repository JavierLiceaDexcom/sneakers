package com.xavidev.testessential.ui.sale.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.source.local.entity.Address
import com.xavidev.testessential.databinding.ItemAddressSelectionBinding

typealias CardSelectionListener = (Address, Int) -> Unit

class AddressSelectionAdapter(private val itemListener: CardSelectionListener) :
    ListAdapter<Address, AddressSelectionAdapter.ViewHolder>(AddressSelectionCallback) {

    inner class ViewHolder(private val binding: ItemAddressSelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(addressItem: Address, itemListener: CardSelectionListener) = with(binding) {
            address = addressItem
            rdbAddressSelection.setOnClickListener {
                itemListener(addressItem, adapterPosition)
            }
            executePendingBindings()
        }
    }

    companion object {
        object AddressSelectionCallback : DiffUtil.ItemCallback<Address>() {
            override fun areItemsTheSame(oldItem: Address, newItem: Address) =
                newItem.id == oldItem.id

            override fun areContentsTheSame(oldItem: Address, newItem: Address) = newItem == oldItem
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