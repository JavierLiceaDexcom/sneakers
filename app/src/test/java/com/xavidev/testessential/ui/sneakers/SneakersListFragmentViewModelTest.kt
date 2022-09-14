package com.xavidev.testessential.ui.sneakers

import com.xavidev.testessential.data.dao.BrandsDao
import com.xavidev.testessential.data.dao.SneakersDao
import com.xavidev.testessential.data.entity.Sneaker
import com.xavidev.testessential.data.entity.SneakerComplete
import com.xavidev.testessential.getOrAwaitValue
import com.xavidev.testessential.resources.SneakersResources
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


internal class SneakersListFragmentViewModelTest {

    private val sneakerDao = mockk<SneakersDao>()
    private val brandsDao = mockk<BrandsDao>()
    private lateinit var sneakersResources: SneakersResources
    private lateinit var viewModel: SneakersListFragmentViewModel

    @Before
    fun setup() {
        sneakersResources = SneakersResources(sneakerDao, brandsDao)
        viewModel = SneakersListFragmentViewModel(sneakersResources, sneakersResources)
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
    fun getAllSneakerFromDBRetrievesCorrectListOfSneakers() {
        // viewModel.getAllSneakersComplete()
        // assertEquals(viewModel.sneakersList.getOrAwaitValue(), listOf<SneakerComplete>())
    }
}