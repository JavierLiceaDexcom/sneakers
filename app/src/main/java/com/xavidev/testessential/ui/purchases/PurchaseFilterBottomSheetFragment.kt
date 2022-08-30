package com.xavidev.testessential.ui.purchases

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xavidev.testessential.R
import com.xavidev.testessential.databinding.FragmentPurchaseFilterBottomSheetBinding

class PurchaseFilterBottomSheetFragment : BottomSheetDialogFragment() {

    val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPurchaseFilterBottomSheetBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onStart() {
        super.onStart()
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}