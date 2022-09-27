package com.xavidev.testessential.ui.paymentMethods

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.xavidev.testessential.R
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.databinding.ActivityPaymentMethodsBinding
import com.xavidev.testessential.ui.addEditCards.CardFormFragment
import com.xavidev.testessential.ui.addEditCards.OnCardAdded
import com.xavidev.testessential.ui.paymentMethods.adapters.CardsAdapter
import com.xavidev.testessential.ui.paymentMethods.adapters.OnCardItemClick
import com.xavidev.testessential.ui.paymentMethods.adapters.OnCardRemoveClick
import com.xavidev.testessential.utils.EventObserver
import com.xavidev.testessential.utils.ViewModelFactory
import com.xavidev.testessential.utils.showAlertDialog

class PaymentMethodsActivity : AppCompatActivity(), OnCardAdded {

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
            showRemoveConfirmationDialog(cardId)
        }
    }

    private fun showRemoveConfirmationDialog(cardId: String) {
        showAlertDialog(
            titleId = R.string.text_remove_card_title,
            messageId = R.string.text_remove_card_message,
            onAccept = object : () -> Unit {
                override fun invoke() {
                    // Remove card
                }
            })
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
            finish()
        }

        setCardList()

        viewModel.openFormEvent.observe(this, EventObserver {
            CardFormFragment().show(supportFragmentManager, CardFormFragment.TAG)
        })
    }

    private fun setCardList() {
        binding.rvPaymentMethods.apply {
            adapter = cardAdapter
            layoutManager = LinearLayoutManager(this@PaymentMethodsActivity)
        }

        viewModel.cards.observe(this) { list ->
            cardAdapter.submitList(list)
        }
    }

    override fun cardAdded() {
        viewModel.getCards()
    }
}