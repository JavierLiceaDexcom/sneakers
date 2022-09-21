package com.xavidev.testessential.ui.purchases

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.databinding.FragmentPurchasesBinding
import com.xavidev.testessential.utils.ViewModelFactory


class PurchasesFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPurchasesBinding.inflate(layoutInflater)
    }
    private val viewModel: PurchasesViewModel by viewModels {
        ViewModelFactory(
            purchaseRepository = (requireContext().applicationContext as SneakersApplication).purchaseRepository,
            owner = this
        )
    }

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

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}