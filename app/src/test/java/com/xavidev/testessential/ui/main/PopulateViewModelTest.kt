package com.xavidev.testessential.ui.main

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.google.common.truth.Truth.assertThat
import com.xavidev.testessential.SneakersApplication
import com.xavidev.testessential.data.source.local.entity.*
import com.xavidev.testessential.data.source.repository.PopulateRepositoryFake
import com.xavidev.testessential.getOrAwaitValue
import com.xavidev.testessential.rules.MainCoroutineRule
import com.xavidev.testessential.utils.JsonParserUtils
import io.mockk.coVerify
import io.mockk.junit4.MockKRule
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
internal class PopulateViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val mockKRule = MockKRule(this)

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var context: Context

    private lateinit var viewModel: PopulateViewModel

    private val populateRepository = spyk<PopulateRepositoryFake>()

    @Before
    fun setup() {
        context = getApplicationContext<SneakersApplication>()
        viewModel = PopulateViewModel(populateRepository)
    }

    @Test
    fun getSneakersCountShouldGetZeroCount() {
        viewModel.getSneakersCount()
        coVerify { populateRepository.getSneakersCount() }
        assertThat(viewModel.sneakersCount.getOrAwaitValue()).isEqualTo(0)
    }

    @Test
    fun populateDatabaseShouldInsertAllDataInDB() {
        val types =
            JsonParserUtils.getObjectListFromJSON(Type::class.java, "types", context)

        val currencies =
            JsonParserUtils.getObjectListFromJSON(Currency::class.java, "currency", context)

        val brands =
            JsonParserUtils.getObjectListFromJSON(Brand::class.java, "brands", context)

        val images =
            JsonParserUtils.getObjectListFromJSON(Images::class.java, "images", context)

        val sneakers =
            JsonParserUtils.getObjectListFromJSON(Sneaker::class.java, "sneakers", context)

        viewModel.populateDatabase(context)
        coVerify { populateRepository.populateCurrenciesTable(currencies) }
        coVerify { populateRepository.populateTypesTable(types) }
        coVerify { populateRepository.insertBrands(brands) }
        coVerify { populateRepository.populateSneakersTable(sneakers) }
        coVerify { populateRepository.insertImages(images) }
    }

    @Test
    fun getSneakersCountShouldReturnFour() = runTest{
        val sneakers =
            JsonParserUtils.getObjectListFromJSON(Sneaker::class.java, "sneakers", context)
        populateRepository.populateSneakersTable(sneakers).collect{}
        coVerify { populateRepository.populateSneakersTable(sneakers) }
        delay(3000L)
        viewModel.getSneakersCount()
        assertThat(viewModel.sneakersCount.getOrAwaitValue()).isEqualTo(4)
    }
}