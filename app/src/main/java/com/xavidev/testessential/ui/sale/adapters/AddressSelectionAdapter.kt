package com.xavidev.testessential.ui.sale.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.entity.Address
import com.xavidev.testessential.databinding.ItemAddressSelectionBinding

class AddressSelectionAdapter(private val itemListener: (Address, Int) -> Unit) :
    ListAdapter<Address, AddressSelectionAdapter.ViewHolder>(AddressSelectionCallback) {

    inner class ViewHolder(binding: ItemAddressSelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(addressItem: Address, itemListener: (Address, Int) -> Unit) {
            // Bind element
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