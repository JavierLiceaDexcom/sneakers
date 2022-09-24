package com.xavidev.testessential.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.databinding.FragmentProfileBinding
import com.xavidev.testessential.ui.address.AddressesActivity
import com.xavidev.testessential.utils.EventObserver
import com.xavidev.testessential.utils.ViewModelFactory
import com.xavidev.testessential.utils.startNewActivity

class ProfileFragment : Fragment() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    private val viewModel: ProfileViewModel by activityViewModels {
        ViewModelFactory(
            userRepository = (requireContext().applicationContext as SneakersApplication).userRepository,
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

        viewModel.openAddressesEvent.observe(viewLifecycleOwner, EventObserver{
            requireActivity().startNewActivity(targetActivity = AddressesActivity(), finish = false)
        })
    }
}