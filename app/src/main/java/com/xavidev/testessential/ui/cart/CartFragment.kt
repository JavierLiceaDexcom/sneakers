package com.xavidev.testessential.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.databinding.FragmentCartBinding
import com.xavidev.testessential.utils.ViewModelFactory

class CartFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentCartBinding.inflate(layoutInflater)
    }

    private val viewModel: CartViewModel by activityViewModels {
        ViewModelFactory(
            cartRepository = (requireContext().applicationContext as SneakersApplication).cartRepository,
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
}