package com.xavidev.testessential.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xavidev.testessential.databinding.FragmentCardFormBinding

class CardFormFragment : Fragment() {
    val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentCardFormBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root
}