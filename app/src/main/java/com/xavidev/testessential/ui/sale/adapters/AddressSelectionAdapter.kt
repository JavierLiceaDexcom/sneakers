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

    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    inner class ViewHolder(private val binding: ItemAddressSelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(addressItem: Address, itemListener: CardSelectionListener) = with(binding) {
            address = addressItem
            if (addressItem.isDefault) {
                selectedItemPos = adapterPosition
                selectAddress()
            }
            rdbAddressSelection.setOnClickListener { handleItemClick(addressItem) }
            clAddressItem.setOnClickListener { handleItemClick(addressItem) }
            executePendingBindings()
        }

        private fun handleItemClick(addressItem: Address) {
            itemListener(addressItem, adapterPosition)
            selectedItemPos = adapterPosition
            lastItemSelectedPos = if (lastItemSelectedPos == -1) selectedItemPos else {
                notifyItemChanged(lastItemSelectedPos)
                selectedItemPos
            }
            notifyItemChanged(selectedItemPos)
        }

        fun selectAddress() {
            binding.rdbAddressSelection.isChecked = true
        }

        fun unselectAddress() {
            binding.rdbAddressSelection.isChecked = false
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
        if (position == selectedItemPos) holder.selectAddress() else holder.unselectAddress()
        holder.bind(currentList[position], itemListener)
    }
}