package com.xavidev.testessential.ui.address.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.data.source.local.entity.Address
import com.xavidev.testessential.databinding.ItemAddressBinding

typealias OnAddressItemClick = (String) -> Unit
typealias OnOptionsItemEdit = (String, Int) -> Unit

class AddressesAdapter(
    private val itemListener: OnAddressItemClick,
    private val optionsListener: OnOptionsItemEdit
) : ListAdapter<Address, AddressesAdapter.ViewHolder>(AddressDiffCallback()) {

    inner class ViewHolder(val binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            addressEntity: Address,
            itemListener: OnAddressItemClick,
            optionsListener: OnOptionsItemEdit
        )= with(binding) {
            address = addressEntity
            clAddressItem.setOnClickListener { itemListener(addressEntity.id) }
            imgMoreOptions.setOnClickListener { optionsListener(addressEntity.id, this@ViewHolder.adapterPosition) }
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddressBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], itemListener, optionsListener)
    }
}

class AddressDiffCallback() : DiffUtil.ItemCallback<Address>() {
    override fun areItemsTheSame(oldItem: Address, newItem: Address) = newItem.id == oldItem.id

    override fun areContentsTheSame(oldItem: Address, newItem: Address) = newItem == oldItem
}