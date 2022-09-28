package com.xavidev.testessential.ui.addEditAddress

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.data.source.local.entity.Address
import com.xavidev.testessential.databinding.FragmentAddressFormBinding
import com.xavidev.testessential.utils.*
import java.util.*

class AddressFormFragment : DialogFragment() {

    companion object {
        const val TAG = "AddressFormFragment"
        const val ADDRESS_ID_EXTRA = "AddressFormFragment"
    }

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentAddressFormBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<AddEditAddressViewModel> {
        ViewModelFactory(
            addressRepository = (requireContext().applicationContext as SneakersApplication).addressRepository,
            owner = this
        )
    }

    private lateinit var onAddressSavedListener: OnAddressSavedListener

    private var addressId: String? = null

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

        addressId = arguments?.getString(ADDRESS_ID_EXTRA)
        viewModel.start(addressId)

        binding.tbrAddressForm.setNavigationOnClickListener {
            showConfirmationDialog()
        }

        handleObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onAddressSavedListener = context as OnAddressSavedListener
    }

    private fun handleObservers() = with(viewModel) {
        saveAddressEvent.observe(viewLifecycleOwner, EventObserver {
            if (!isValidForm()) return@EventObserver
            viewModel.saveAddress(getForm())
        })

        addressUpdatedEvent.observe(viewLifecycleOwner, EventObserver {
            requireDialog().dismiss()
            onAddressSavedListener.onSaved()
        })

        addressSavedMessage.observe(viewLifecycleOwner, EventObserver { message ->
            requireContext().toast(getString(message))
        })

        addressErrorMessage.observe(requireActivity(), EventObserver {
            view?.setupSnackbar(viewLifecycleOwner, viewModel.addressErrorMessage)
        })
    }

    private fun getForm(): Address {
        val zip = binding.etSearchZipCode.text.toString().toInt()
        val name = binding.etName.text.toString()
        val street = binding.etStreet.text.toString()
        val state = binding.etState.text.toString()
        val municipality = binding.etMunicipality.text.toString()
        val suburb = binding.etSuburb.text.toString()
        val contactNumber = binding.etContactNumber.text.toString()
        val intNumber = binding.etIntNumber.text.toString()
        val extNumber = binding.etExtNumber.text.toString()

        return Address(
            id = addressId ?: UUID.randomUUID().toString(),
            name = name,
            street = street,
            zip = zip,
            state = state,
            municipality = municipality,
            suburb = suburb,
            contactNumber = contactNumber,
            extNumber = extNumber,
            intNumber = intNumber
        ).apply {
            createdAt = System.currentTimeMillis()
        }
    }

    private fun isValidForm(): Boolean {

        val zip = binding.etSearchZipCode.validator().nonEmpty()
            .addErrorCallback {
                binding.etSearchZipCode.error = getString(R.string.text_required_field)
            }
            .addSuccessCallback { binding.etSearchZipCode.error = null }.check()

        val name = binding.etName.validator().nonEmpty()
            .addErrorCallback { binding.etName.error = getString(R.string.text_required_field) }
            .addSuccessCallback { binding.etName.error = null }.check()

        val state = binding.etState.validator().nonEmpty()
            .addErrorCallback { binding.etState.error = getString(R.string.text_required_field) }
            .addSuccessCallback { binding.etState.error = null }.check()

        val municipality = binding.etMunicipality.validator().nonEmpty()
            .addErrorCallback {
                binding.etMunicipality.error = getString(R.string.text_required_field)
            }
            .addSuccessCallback { binding.etMunicipality.error = null }.check()

        val suburb = binding.etSuburb.validator().nonEmpty()
            .addErrorCallback { binding.etSuburb.error = getString(R.string.text_required_field) }
            .addSuccessCallback { binding.etSuburb.error = null }.check()

        val street = binding.etStreet.validator().nonEmpty()
            .addErrorCallback { binding.etStreet.error = getString(R.string.text_required_field) }
            .addSuccessCallback { binding.etStreet.error = null }.check()

        val extNumber = binding.etExtNumber.validator().nonEmpty().startWithNumber()
            .addErrorCallback {
                binding.etExtNumber.error = getString(R.string.text_required_field)
            }
            .addSuccessCallback { binding.etExtNumber.error = null }.check()

        val contactNumber =
            binding.etContactNumber.validator().nonEmpty().onlyNumbers().maxLength(10)
                .addErrorCallback {
                    binding.etContactNumber.error = getString(R.string.text_required_field)
                }
                .addSuccessCallback { binding.etContactNumber.error = null }.check()

        return zip && name && state && municipality && suburb && street && extNumber && contactNumber
    }

    private fun showConfirmationDialog() {
        requireActivity().showAlertDialog(
            R.string.text_exit,
            R.string.text_exit_form_message,
            onAccept = object : () -> Unit {
                override fun invoke() {
                    dialog?.dismiss()
                }
            }
        )
    }
}

interface OnAddressSavedListener {
    fun onSaved()
}