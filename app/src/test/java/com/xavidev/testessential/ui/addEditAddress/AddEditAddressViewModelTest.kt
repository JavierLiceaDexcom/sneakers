package com.xavidev.testessential.ui.addEditAddress

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xavidev.testessential.R
import com.xavidev.testessential.data.repository.AddressRepository
import com.xavidev.testessential.data.source.local.entity.Address
import com.xavidev.testessential.getOrAwaitValue
import com.xavidev.testessential.utils.AddressTestUtils
import com.xavidev.testessential.utils.Event
import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class AddEditAddressViewModelTest {

    //TODO: Verify livedata variables in vm

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AddEditAddressViewModel

    private val addressRepository = mockk<AddressRepository>(relaxed = true)

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = AddEditAddressViewModel(addressRepository)
    }

    @Test
    fun when_addressId_isNull_expect_prepareToCreateNewAddress() {
        viewModel.start(null)
        val isNewAddressField = viewModel::class.java.getDeclaredField("_isNewAddress")
        isNewAddressField.isAccessible = true
        val isNewAddressValue = isNewAddressField.get(viewModel) as Boolean
        assertThat(viewModel.actionText.getOrAwaitValue(), equalTo(R.string.text_save_address))
        assertThat(isNewAddressValue, equalTo(true))
    }

    @Test
    fun when_addressId_isNotNull_expect_prepareToUpdateAddress() {
        viewModel.start(UUID.randomUUID().toString())
        val isNewAddressField = viewModel::class.java.getDeclaredField("_isNewAddress")
        isNewAddressField.isAccessible = true
        val isNewAddressValue = isNewAddressField.get(viewModel) as Boolean
        assertThat(viewModel.actionText.getOrAwaitValue(), equalTo(R.string.text_update_address))
        assertThat(isNewAddressValue, equalTo(false))
    }

    @Test
    fun when_addressId_isNotNull_expect_getAddressFromDb() {
        val address = AddressTestUtils.getSingleAddress()
        viewModel.start(address.id)
        verify(exactly = 1) { addressRepository.observeAddressById(address.id) }
    }

    @Test
    fun when_saveAddress_expected_insertAddressToDb() {
        val address = AddressTestUtils.getSingleAddress()
        viewModel.start(null)
        viewModel.saveOrUpdateAddress(address)
        verify { runTest { addressRepository.insertAddress(address) } }
    }

    @Test
    fun when_updateAddress_expected_updateAddressInDb() {
        val address = AddressTestUtils.getSingleAddress()
        viewModel.start(address.id)
        viewModel.saveOrUpdateAddress(address)
        verify { runTest { addressRepository.updateAddress(address) } }
    }
}