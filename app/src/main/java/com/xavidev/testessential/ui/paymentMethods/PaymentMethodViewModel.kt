package com.xavidev.testessential.ui.paymentMethods

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.R
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.CardRepository
import com.xavidev.testessential.data.source.local.entity.Card
import com.xavidev.testessential.utils.NavigationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class PaymentMethodViewModel(private val cardRepository: CardRepository): NavigationViewModel() {

    fun openCardFormFragment(view: View){
        navigateTo(view, R.id.action_paymentMethodsFragment_to_cardFormFragment)
    }

    private val _cards = MutableLiveData<List<Card>?>()
    val cards: LiveData<List<Card>?> get() = _cards

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    fun getCards() = viewModelScope.launch {
        cardRepository.getAllCards().flowOn(Dispatchers.IO)
            .collect{result->
                if (result is Result.Success){
                    _cards.postValue(result.data)
                    _isEmpty.postValue(result.data.isEmpty())
                } else {
                    _isEmpty.postValue(true)
                }
            }
    }
}