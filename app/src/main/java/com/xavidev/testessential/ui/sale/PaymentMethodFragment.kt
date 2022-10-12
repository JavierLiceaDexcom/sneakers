package com.xavidev.testessential.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.data.source.local.entity.Card
import com.xavidev.testessential.databinding.FragmentPaymentMethodBinding
import com.xavidev.testessential.ui.sale.adapters.PaymentMethodSelectionAdapter
import com.xavidev.testessential.utils.ViewModelFactory


class PaymentMethodFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPaymentMethodBinding.inflate(layoutInflater)
    }

    private val viewModel: SaleViewModel by viewModels{
        ViewModelFactory(
            sneakersRepository = (requireContext().applicationContext as SneakersApplication).sneakersRepository,
            addressRepository = (requireContext().applicationContext as SneakersApplication).addressRepository,
            owner = this
        )
    }

    private val paymentMethodSelectionAdapter =
        PaymentMethodSelectionAdapter(object : (Card, Int) -> Unit {
            override fun invoke(card: Card, pos: Int) {
                // Code here
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

        binding.tbrPaymentMethodSelection.setNavigationOnClickListener {
            viewModel.navigateTo(view, R.id.action_paymentMethodFragment2_to_orderAddressFragment2)
        }
    }
}