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
import com.xavidev.testessential.databinding.FragmentOrderConfirmationBinding
import com.xavidev.testessential.ui.sale.adapters.OrderReviewAdapter

class OrderConfirmationFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentOrderConfirmationBinding.inflate(layoutInflater)
    }

    private val viewModel: SaleViewModel by activityViewModels()

    private val orderAdapter = OrderReviewAdapter()

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

        binding.tbrConfirmOrder.setNavigationOnClickListener {
            val action = R.id.action_orderConfirmationFragment_to_paymentMethodFragment2
            viewModel.navigateTo(view, action)
        }

        viewModel.getSelectedSneakers()
        loadOrderItemsList()
    }

    private fun loadOrderItemsList() {
        binding.rvOrderItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = orderAdapter
        }
        viewModel.orderSneakers.observe(viewLifecycleOwner) { list ->
            orderAdapter.submitList(list)
        }
    }
}