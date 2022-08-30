package com.xavidev.testessential.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xavidev.testessential.databinding.FragmentPurchaseFinishedDialogBinding

class PurchaseFinishedDialogFragment : BottomSheetDialogFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPurchaseFinishedDialogBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root
}