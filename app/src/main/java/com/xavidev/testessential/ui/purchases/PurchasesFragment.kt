package com.xavidev.testessential.ui.purchases

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.xavidev.testessential.databinding.FragmentPurchasesBinding
import com.xavidev.testessential.ui.BaseFragment


class PurchasesFragment :  BaseFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPurchasesBinding.inflate(layoutInflater)
    }
    private val viewModel: PurchasesViewModel by viewModels()

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
    }
}