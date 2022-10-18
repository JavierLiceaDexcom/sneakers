package com.xavidev.testessential.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.databinding.FragmentOrderAddressBinding
import com.xavidev.testessential.utils.EventObserver
import com.xavidev.testessential.utils.ViewModelFactory
import com.xavidev.testessential.utils.setupSnackbar


class OrderAddressFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentOrderAddressBinding.inflate(layoutInflater)
    }

    private val viewModel: SaleViewModel by activityViewModels()

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

        viewModel.getDefaultAddress()
        viewModel.getSelectedSneakers()
        viewModel.deliveryErrorEvent.observe(viewLifecycleOwner, EventObserver {
            requireView().setupSnackbar(viewLifecycleOwner, viewModel.deliveryErrorEvent)
        })

        binding.tbrDeliveryMethod.setNavigationOnClickListener {
            requireActivity().finish()
        }
    }

}