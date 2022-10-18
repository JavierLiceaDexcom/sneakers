package com.xavidev.testessential.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xavidev.testessential.R
import com.xavidev.testessential.data.source.local.entity.Card
import com.xavidev.testessential.databinding.FragmentPaymentMethodBinding
import com.xavidev.testessential.ui.sale.adapters.PaymentItemClickListener
import com.xavidev.testessential.ui.sale.adapters.PaymentMethodSelectionAdapter
import com.xavidev.testessential.utils.EventObserver
import com.xavidev.testessential.utils.setupSnackbar
import com.xavidev.testessential.utils.toast

class PaymentMethodFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPaymentMethodBinding.inflate(layoutInflater)
    }

    private val viewModel: SaleViewModel by activityViewModels()

    private val paymentMethodSelectionAdapter =
        PaymentMethodSelectionAdapter(object : PaymentItemClickListener {
            override fun invoke(card: Card, pos: Int) {
                setSelectedCardId(card.id)
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

        viewModel.getAllCards()
        viewModel.noCardErrorEvent.observe(viewLifecycleOwner, EventObserver {
            requireContext().toast(getString(R.string.text_no_card_selected_error))
        })
        loadCardsList()
    }

    private fun loadCardsList() {
        binding.recyclerCards.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = paymentMethodSelectionAdapter
        }

        viewModel.allCards.observe(viewLifecycleOwner) { list ->
            paymentMethodSelectionAdapter.submitList(list)
        }
    }

    private fun setSelectedCardId(id: String) {
        viewModel.setSelectedCardId(id)
    }
}