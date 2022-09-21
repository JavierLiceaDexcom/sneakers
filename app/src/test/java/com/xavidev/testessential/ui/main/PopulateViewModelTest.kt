package com.xavidev.testessential.ui.main

import com.xavidev.testessential.MainCoroutineRule
import com.xavidev.testessential.data.resources.PopulateResources
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class PopulateViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var viewModel: PopulateViewModel
    private val populateResources = mockk<PopulateResources>()

    @Before
    fun setup() {
        viewModel = PopulateViewModel(populateResources)
    }

    @Test
    fun onInsertSneakerListToDBReturnsSuccess() {
        /*val insertBrands = viewModel.javaClass.getDeclaredMethod("insertBrands", brands::class.java)
        insertBrands.isAccessible = true
        insertBrands.invoke(viewModel, brands)*/
    }
}