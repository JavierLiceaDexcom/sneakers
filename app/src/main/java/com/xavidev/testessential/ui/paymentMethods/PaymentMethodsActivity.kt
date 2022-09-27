package com.xavidev.testessential.ui.paymentMethods

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.databinding.ActivityPaymentMethodsBinding
import com.xavidev.testessential.ui.paymentMethods.adapters.CardsAdapter
import com.xavidev.testessential.ui.paymentMethods.adapters.OnCardItemClick
import com.xavidev.testessential.ui.paymentMethods.adapters.OnCardRemoveClick
import com.xavidev.testessential.utils.ViewModelFactory

class PaymentMethodsActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityPaymentMethodsBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<PaymentMethodViewModel> {
        ViewModelFactory(
            cardsRepository = (this.applicationContext as SneakersApplication).cardRepository,
            owner = this
        )
    }

    private val cardItemClickListener = object : OnCardItemClick {
        override fun invoke(cardId: String) {
            // Open card details fragment
        }
    }

    private val cardItemRemoveListener = object : OnCardRemoveClick {
        override fun invoke(cardId: String) {
            // Show remove confirmation dialog
        }
    }

    private val cardAdapter = CardsAdapter(cardItemClickListener, cardItemRemoveListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            lifecycleOwner = this@PaymentMethodsActivity
            vm = viewModel
        }

        viewModel.getCards()

        binding.tbrPaymentMethods.setNavigationOnClickListener {
            viewModel.navigateTo(
                binding.root,
                R.id.action_paymentMethodsFragment_to_profileFragment
            )
        }

        setCardList()
    }

    private fun setCardList() {
        binding.rvPaymentMethods.apply {
            adapter = cardAdapter
            layoutManager = LinearLayoutManager(this@PaymentMethodsActivity)
        }
    }
}