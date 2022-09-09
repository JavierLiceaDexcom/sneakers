package com.xavidev.testessential.ui.main

import com.xavidev.testessential.MainCoroutineRule
import com.xavidev.testessential.data.entity.*
import com.xavidev.testessential.resources.PopulateResources
import com.xavidev.testessential.utils.JsonParserUtils
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

    val sneakerTypes = JsonParserUtils.getObjectListFromJSON(Type::class.java, "types")
    val currencies = JsonParserUtils.getObjectListFromJSON(Currency::class.java, "currency")
    val brands = JsonParserUtils.getObjectListFromJSON(Brand::class.java, "brands")
    val images = JsonParserUtils.getObjectListFromJSON(Images::class.java, "images")
    val sneakers = JsonParserUtils.getObjectListFromJSON(Sneaker::class.java, "sneakers")

    private lateinit var viewModel: PopulateViewModel
    private val populateResources = mockk<PopulateResources>()

    @Before
    fun setup() {
        viewModel = PopulateViewModel(populateResources)
    }

    @Test
    fun onInsertSneakerListToDBReturnsSuccess() {
        val insertBrands = viewModel.javaClass.getDeclaredMethod("insertBrands", brands::class.java)
        insertBrands.isAccessible = true
        insertBrands.invoke(viewModel, brands)
    }
}