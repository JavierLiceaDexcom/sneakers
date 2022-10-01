package com.xavidev.testessential.ui.sneakers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.xavidev.testessential.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
 class ExampleViewModelTest {

     @get:Rule
     val instantTaskRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExampleViewModel

    @Before
    fun setUp() {
        viewModel = ExampleViewModel()
    }

    @Test
    fun setStringValueShouldSetLiveDataString() {
        val strValue = "My String value"
        val intValue = 876
        val boolValue = true

        viewModel.setValue(boolValue)
        assertThat(viewModel.myValue.getOrAwaitValue()).isEqualTo(boolValue)
    }
}