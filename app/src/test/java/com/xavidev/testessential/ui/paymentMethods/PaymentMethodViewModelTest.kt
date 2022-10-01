package com.xavidev.testessential.ui.paymentMethods

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.xavidev.testessential.data.source.repository.CardRepositoryFake
import com.xavidev.testessential.getOrAwaitValue
import com.xavidev.testessential.rules.MainCoroutineRule
import com.xavidev.testessential.utils.CardTestUtils
import io.mockk.coVerify
import io.mockk.junit4.MockKRule
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
internal class PaymentMethodViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockKRule = MockKRule(this)

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: PaymentMethodViewModel

    private val cardRepository = spyk<CardRepositoryFake>()

    @Before
    fun setUp() {
        viewModel = PaymentMethodViewModel(cardRepository)
    }

    @Test
    fun when_getCardsList_expected_getEmptyListOfCards() {
        viewModel.getCards()
        coVerify(exactly = 1) { cardRepository.getAllCards() }
        assertThat(viewModel.isEmpty.getOrAwaitValue()).isTrue()
        assertThat(viewModel.cards.getOrAwaitValue()).isEmpty()
    }

    private fun insertCards() = runTest {
        CardTestUtils.getListOfCards().forEach { card ->
            cardRepository.insertCard(card).collect {}
        }
    }

    @Test
    fun when_getCardsList_expected_getListOfCardsSuccess() {
        insertCards()
        viewModel.getCards()
        coVerify(exactly = 1) { cardRepository.getAllCards() }
        assertThat(viewModel.cards.getOrAwaitValue()).isNotEmpty()
        assertThat(viewModel.cards.getOrAwaitValue()).isEqualTo(CardTestUtils.getListOfCards())
    }

    @Test
    fun when_removeCardById_expect_cardRemovedEventIsTriggered() {
        insertCards()
        val firstCard = CardTestUtils.getListOfCards()[0]
        viewModel.removeCardById(firstCard.id)
        viewModel.getCards()
        coVerify(exactly = 1) { cardRepository.deleteCardById(firstCard.id) }
        assertThat(viewModel.cardRemovedEvent.getOrAwaitValue().peekContent()).isEqualTo(Unit)
        assertThat(viewModel.cards.getOrAwaitValue()).doesNotContain(firstCard)
    }
}