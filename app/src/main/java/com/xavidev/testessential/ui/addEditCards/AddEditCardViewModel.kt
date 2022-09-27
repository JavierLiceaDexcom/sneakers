package com.xavidev.testessential.ui.addEditCards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.R
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.CardRepository
import com.xavidev.testessential.data.source.local.entity.Card
import com.xavidev.testessential.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class AddEditCardViewModel(private val cardRepository: CardRepository) : ViewModel() {

    companion object {
        private const val ONE_SECOND_DELAY = 1000L
    }

    private val _cardSavedMessage = MutableLiveData<Event<Int>>()
    val cardSavedMessage: LiveData<Event<Int>> get() = _cardSavedMessage

    // Events

    private val _saveCardEvent = MutableLiveData<Event<Unit>>()
    val saveCardEvent: LiveData<Event<Unit>> get() = _saveCardEvent

    private val _cardSavedEvent = MutableLiveData<Event<Unit>>()
    val cardSavedEvent: LiveData<Event<Unit>> get() = _cardSavedEvent

    fun onSaveCard() {
        _saveCardEvent.value = Event(Unit)
    }

    fun saveCard(card: Card) = viewModelScope.launch {
        cardRepository.insertCard(card).flowOn(Dispatchers.IO).collect { result ->
            if (result is Result.Success) {
                delay(ONE_SECOND_DELAY)
                _cardSavedEvent.postValue(Event(Unit))
            } else {
                _cardSavedMessage.postValue(Event(R.string.text_card_saved_error))
            }
        }
    }
}