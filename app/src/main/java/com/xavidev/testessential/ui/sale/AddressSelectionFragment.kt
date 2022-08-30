package com.xavidev.testessential.ui.sale

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xavidev.testessential.R
import com.xavidev.testessential.databinding.FragmentAddressSelectionBinding


class AddressSelectionFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentAddressSelectionBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

}