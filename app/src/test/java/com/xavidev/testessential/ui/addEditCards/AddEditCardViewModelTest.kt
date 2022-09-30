package com.xavidev.testessential.ui.addEditCards


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.xavidev.testessential.MainCoroutineRule
import com.xavidev.testessential.data.source.repository.CardRepositoryFake
import com.xavidev.testessential.getOrAwaitValue
import com.xavidev.testessential.utils.CardTestUtils
import io.mockk.junit4.MockKRule
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class AddEditCardViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: AddEditCardViewModel

    private val addEditCardRepository = spyk<CardRepositoryFake>()

    @Before
    fun setUp() {
        viewModel = AddEditCardViewModel(addEditCardRepository)
    }

    @Test
    fun when_addCard_expect_cardSavedEventIsTriggered() {
        val card = CardTestUtils.getSingleCard()
        viewModel.saveCard(card)
        verify(exactly = 1) { runTest { addEditCardRepository.insertCard(card) } }
        assertThat(viewModel.cardSavedEvent.getOrAwaitValue().peekContent()).isEqualTo(Unit)
    }
}