package com.xavidev.testessential.ui.addEditCards

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.data.source.local.entity.Card
import com.xavidev.testessential.databinding.FragmentCardFormBinding
import com.xavidev.testessential.utils.*

class CardFormFragment : DialogFragment() {

    companion object {
        const val TAG = "CardFormFragment"
    }

    val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentCardFormBinding.inflate(layoutInflater)
    }

    private var onCardAddedListener: OnCardAdded? = null

    private val viewModel by viewModels<AddEditCardViewModel> {
        ViewModelFactory(
            cardsRepository = (requireContext().applicationContext as SneakersApplication).cardRepository,
            owner = this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onCardAddedListener = context as OnCardAdded
    }

    override fun getTheme() = R.style.FullscreenDialogTheme

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        binding.tbrAddPayment.setNavigationOnClickListener {
            showConfirmationDialog()
        }
        setupForm()
        handleObservers()
    }

    private fun setupForm() {
        val bankingInstitutionsAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_expandable_list_item_1,
            resources.getStringArray(R.array.banks)
        )
        binding.atvBankingInstitution.setAdapter(bankingInstitutionsAdapter)

        binding.atvBankingInstitution.setOnFocusChangeListener { _, focused ->
            if (focused) binding.atvBankingInstitution.showDropDown()
        }
    }

    private fun handleObservers() {
        viewModel.saveCardEvent.observe(viewLifecycleOwner, EventObserver {
            if (!validateCardForm()) return@EventObserver
            viewModel.saveCard(getFormData())
        })

        viewModel.cardSavedEvent.observe(viewLifecycleOwner, EventObserver {
            onCardAddedListener?.cardAdded()
            dialog?.dismiss()
        })

        viewModel.cardSavedMessage.observe(viewLifecycleOwner, EventObserver {
            view?.setupSnackbar(viewLifecycleOwner, viewModel.cardSavedMessage)
        })
    }

    private fun getFormData(): Card {
        val cardNumber = binding.etCardNumber.text.toString()
        val beneficiary = binding.etBeneficiary.text.toString()
        val expirationDate = binding.etExpirationDate.text.toString()
        val cvv = binding.etCvv.text.toString().toInt()
        val isDefault = binding.cbxDefaultPayment.isChecked
        val bankingInstitution = binding.atvBankingInstitution.text.toString()

        return Card(
            institutionName = bankingInstitution,
            cardNumber = cardNumber,
            expirationDate = expirationDate,
            ownerName = beneficiary,
            cardCVV = cvv,
            isDefault = isDefault
        )
    }

    private fun validateCardForm(): Boolean {
        val requiredFieldMessage = getString(R.string.text_required_field)

        val isCardNumberValid = binding.etCardNumber.validator().creditCardNumber(
            creditCardErrorMsg = getString(R.string.text_card_format_error)
        )
            .addErrorCallback { binding.etCardNumber.error = requiredFieldMessage }
            .addSuccessCallback { binding.etCardNumber.error = null }
            .check()

        val isBeneficiaryValid = binding.etBeneficiary.validator().nonEmpty()
            .addErrorCallback { binding.etBeneficiary.error = requiredFieldMessage }
            .addSuccessCallback { binding.etBeneficiary.error = null }.check()

        val isExpirationValid = binding.etExpirationDate.validator().nonEmpty()
            .addErrorCallback { binding.etExpirationDate.error = requiredFieldMessage }
            .addSuccessCallback { binding.etExpirationDate.error = null }.check()

        val isCVVValid = binding.etCvv.validator().nonEmpty().onlyNumbers()
            .addErrorCallback { binding.etCvv.error = requiredFieldMessage }
            .addSuccessCallback { binding.etCvv.error = null }.check()

        val isInstitutionValid = binding.atvBankingInstitution.validator().nonEmpty()
            .addErrorCallback { binding.atvBankingInstitution.error = requiredFieldMessage }
            .addSuccessCallback { binding.atvBankingInstitution.error = null }.check()

        return isInstitutionValid && isCardNumberValid && isBeneficiaryValid && isExpirationValid && isCVVValid
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

interface OnCardAdded {
    fun cardAdded()
}