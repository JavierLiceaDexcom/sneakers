package com.xavidev.testessential.ui.sneakers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.xavidev.testessential.rules.MainCoroutineRule
import com.xavidev.testessential.data.source.repository.BrandsRepositoryFake
import com.xavidev.testessential.getOrAwaitValue
import com.xavidev.testessential.utils.BrandTestUtils
import io.mockk.coVerify
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
internal class BrandsViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutine = MainCoroutineRule()

    private lateinit var viewModel: BrandsViewModel

    private val brandRepository = spyk<BrandsRepositoryFake>()

    @Before
    fun setUp() {
        viewModel = BrandsViewModel(brandRepository)
        brandRepository.insertBrands(BrandTestUtils.getListOfBrands())
    }

    @Test
    fun getBrands_getListOfBrandsSuccess() {
        viewModel.getBrands()
        coVerify { brandRepository.getBrands() }
        assertThat(viewModel.brandsList.getOrAwaitValue()).isNotEmpty()
        assertThat(viewModel.brandsList.getOrAwaitValue()).isEqualTo(BrandTestUtils.getListOfBrands())
    }
}