package com.xavidev.testessential.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xavidev.testessential.R
import com.xavidev.testessential.databinding.FragmentPurchaseFinishedDialogBinding

class PurchaseFinishedDialogFragment : BottomSheetDialogFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPurchaseFinishedDialogBinding.inflate(layoutInflater)
    }

    private val viewModel: SaleViewModel by activityViewModels()

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

        binding.btnClose.setOnClickListener {
            requireActivity().finish()
        }
    }

    override fun onStart() {
        super.onStart()
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun getTheme() = R.style.ModalBottomSheetDialog
}