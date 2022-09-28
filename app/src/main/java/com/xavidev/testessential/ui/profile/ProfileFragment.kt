package com.xavidev.testessential.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.databinding.FragmentProfileBinding
import com.xavidev.testessential.ui.address.AddressesActivity
import com.xavidev.testessential.ui.intro.IntroActivity
import com.xavidev.testessential.ui.paymentMethods.PaymentMethodsActivity
import com.xavidev.testessential.utils.*

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

        viewModel.openAddressesEvent.observe(viewLifecycleOwner, EventObserver {
            requireActivity().startNewActivity(targetActivity = AddressesActivity(), finish = false)
        })

        viewModel.openCardsEvent.observe(viewLifecycleOwner, EventObserver {
            requireActivity().startNewActivity(
                targetActivity = PaymentMethodsActivity(),
                finish = false
            )
        })

        viewModel.signOutEvent.observe(viewLifecycleOwner, EventObserver {
            showSignOutDialog()
        })

        viewModel.signedOutEvent.observe(viewLifecycleOwner, EventObserver {
            requireActivity().startNewActivity(targetActivity = IntroActivity(), finish = true)
        })
    }

    private fun showSignOutDialog() {
        requireActivity().showAlertDialog(
            titleId = R.string.text_sigout_title,
            messageId = R.string.text_sigout_message,
            onAccept = object : OnDialogAccept {
                override fun invoke() {
                    viewModel.signOut(requireContext())
                }
            }
        )
    }
}