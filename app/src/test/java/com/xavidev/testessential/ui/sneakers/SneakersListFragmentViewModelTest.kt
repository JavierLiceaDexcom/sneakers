package com.xavidev.testessential.ui.sneakers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xavidev.testessential.MainCoroutineRule
import com.xavidev.testessential.data.repository.SneakersRepository
import io.mockk.junit4.MockKRule
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
internal class SneakersListFragmentViewModelTest {

    private lateinit var viewModel: SneakersListFragmentViewModel

    private val sneakersRepository = spyk<SneakersRepository>()

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        viewModel = SneakersListFragmentViewModel(sneakersRepository)
    }

    // List of the tests

    // 1. Verify that the JSON sneaker list is pared to a list of Sneaker class
    // 2. Verify the list obtained have the required elements
    // 3 .Validate the list of sneakers is inserted in the database
    // 4. Validate the lis of sneakers is retrieved correctly from the database
    // 5. Validate the list of sneakers filter by brand is retrieved correctly from the database
    // 6. Validate that the list of sneakers comes empty when no brand associated
    // 7. Validate a sneaker is added to favorites
    // 8. Validate that a sneaker is removed from favorites
    // 9. Validate the sneaker color list is not empty
    // 10. Validate the sneaker colors list is not empty
    // 11. Validate that the sneaker images list is not empty

    @Test
    fun getAllSneakersComplete_listOfSneakersSuccess() {

    }

    @Test
    fun getAllSneakerFromDBRetrievesCorrectListOfSneakers() {
        // viewModel.getAllSneakersComplete()
        // assertEquals(viewModel.sneakersList.getOrAwaitValue(), listOf<SneakerComplete>())
    }
}