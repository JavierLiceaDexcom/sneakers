package com.xavidev.testessential.ui.addEditAddress

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.databinding.FragmentAddressFormBinding
import com.xavidev.testessential.utils.EventObserver
import com.xavidev.testessential.utils.ViewModelFactory


class AddressFormFragment : DialogFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentAddressFormBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<AddEditAddressViewModel> {
        ViewModelFactory(
            addressRepository = (requireContext().applicationContext as SneakersApplication).addressRepository,
            owner = this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun getTheme() = R.style.FullscreenDialogTheme_Primary

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        binding.tbrAddressForm.setNavigationOnClickListener {
            viewModel.navigateTo(view, R.id.action_addressFormFragment_to_addressesFragment)
        }

        handleObservers()
    }

    private fun handleObservers() = with(viewModel) {
        saveAddressEvent.observe(viewLifecycleOwner, EventObserver {
            validateForm()
        })

        addressUpdatedEvent.observe(viewLifecycleOwner, EventObserver {
            // Navigate to the addresses list
        })
    }

    private fun validateForm() {
        // Validate form
        // Send request
    }
}