package com.xavidev.testessential.ui.address.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.testessential.R
import com.xavidev.testessential.data.source.local.entity.Address
import com.xavidev.testessential.databinding.ItemAddressBinding

typealias OnAddressEditClick = (String) -> Unit
typealias OnAddressDefaultClick = (String) -> Unit
typealias OnAddressRemoveClick = (String) -> Unit

class AddressesAdapter(
    private val editAddressListener: OnAddressEditClick,
    private val defaultAddressListener: OnAddressDefaultClick,
    private val removeAddressListener: OnAddressRemoveClick
) : ListAdapter<Address, AddressesAdapter.ViewHolder>(AddressDiffCallback()) {

    inner class ViewHolder(val binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            addressEntity: Address,
        ) = with(binding) {
            address = addressEntity
            imgMoreOptions.setOnClickListener { setItemMenuOptions(addressEntity) }
            executePendingBindings()
        }

        private fun setItemMenuOptions(addressEntity: Address) {
            val addressId = addressEntity.id
            val popupMenu = PopupMenu(binding.root.context, binding.imgMoreOptions)
            popupMenu.inflate(R.menu.address_options_menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.address_default_option -> defaultAddressListener(addressId)
                    R.id.address_edit_option -> editAddressListener(addressId)
                    R.id.address_remove_option -> removeAddressListener(addressId)
                }
                true
            }
            popupMenu.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddressBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class AddressDiffCallback : DiffUtil.ItemCallback<Address>() {
    override fun areItemsTheSame(oldItem: Address, newItem: Address) = newItem.id == oldItem.id

    override fun areContentsTheSame(oldItem: Address, newItem: Address) = newItem == oldItem
}