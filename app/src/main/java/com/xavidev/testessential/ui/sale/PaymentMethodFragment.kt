package com.xavidev.testessential.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xavidev.testessential.databinding.FragmentPaymentMethodBinding


class PaymentMethodFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPaymentMethodBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

}