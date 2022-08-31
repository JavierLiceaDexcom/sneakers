package com.xavidev.testessential.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.xavidev.testessential.R
import com.xavidev.testessential.databinding.FragmentCardFormBinding

class CardFormFragment : DialogFragment() {
    val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentCardFormBinding.inflate(layoutInflater)
    }

    private val viewModel: PaymentMethodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun getTheme() = R.style.FullscreenDialogTheme_Primary

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        binding.tbrAddPayment.setNavigationOnClickListener {
            viewModel.navigateTo(view, R.id.action_cardFormFragment_to_paymentMethodsFragment)
        }
    }
}