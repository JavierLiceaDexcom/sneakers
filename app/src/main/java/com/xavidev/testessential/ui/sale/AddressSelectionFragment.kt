package com.xavidev.testessential.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.data.source.local.entity.Address
import com.xavidev.testessential.databinding.FragmentAddressSelectionBinding
import com.xavidev.testessential.ui.sale.adapters.AddressSelectionAdapter
import com.xavidev.testessential.ui.sale.adapters.CardSelectionListener
import com.xavidev.testessential.utils.ViewModelFactory
import com.xavidev.testessential.utils.toast


class AddressSelectionFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentAddressSelectionBinding.inflate(layoutInflater)
    }

    private val viewModel: SaleViewModel by activityViewModels()

    private val addressSelectionAdapter = AddressSelectionAdapter(object : CardSelectionListener {
        override fun invoke(address: Address, pos: Int) {
            viewModel.setDefaultAddress(address.id)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        viewModel.getAllAddresses()

        handleListeners()
        loadAddressesList()
    }

    private fun loadAddressesList() {
        binding.recyclerAddresses.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = addressSelectionAdapter
        }
        viewModel.allAddresses.observe(viewLifecycleOwner) { list ->
            addressSelectionAdapter.submitList(list)
        }
    }

    private fun handleListeners() = with(binding) {
        btnSelectAddress.setOnClickListener {
            viewModel.navigateTo(root, R.id.action_addressSelectionFragment2_to_orderAddressFragment2)
        }

        tbrSelectAddress.setNavigationOnClickListener {
            viewModel.navigateTo(root, R.id.action_addressSelectionFragment2_to_orderAddressFragment2)
        }
    }
}