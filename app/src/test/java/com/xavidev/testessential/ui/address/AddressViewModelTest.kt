package com.xavidev.testessential.ui.address

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xavidev.testessential.MainCoroutineRule
import com.xavidev.testessential.data.source.repository.AddressRepositoryFake
import com.xavidev.testessential.getOrAwaitValue
import com.xavidev.testessential.utils.AddressTestUtils
import io.mockk.junit4.MockKRule
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class AddressViewModelTest {

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var viewModel: AddressViewModel

    private val addressesRepository = spyk<AddressRepositoryFake>()

    @Before
    fun setUp() {
        viewModel = AddressViewModel(addressesRepository)
    }

    @Test
    fun when_noAddressesInDb_expect_emptyAddressesList() {
        viewModel.getAddresses()
        verify(exactly = 1) { runTest { addressesRepository.getAllAddresses() } }
        assertThat(viewModel.addressesList.getOrAwaitValue(), `is`(emptyList()))
    }

    @Test
    fun when_getEmptyList_expect_isEmptyTrue() {
        viewModel.getAddresses()
        verify(exactly = 1) { runTest { addressesRepository.getAllAddresses() } }
        assertThat(viewModel.isEmpty.getOrAwaitValue(), equalTo(true))
    }

    private fun insertAddresses() = runBlocking {
        AddressTestUtils.getAddressList().forEach { address ->
            addressesRepository.insertAddress(address)
        }
    }

    @Test
    fun when_getAddressesListSuccessful_expect_addressesList() {
        insertAddresses()
        viewModel.getAddresses()
        verify(exactly = 1) { runTest { addressesRepository.getAllAddresses() } }
        assertThat(
            viewModel.addressesList.getOrAwaitValue()?.sortedBy { it.id },
            equalTo(AddressTestUtils.getAddressList().sortedBy { it.id })
        )
        assertThat(viewModel.isEmpty.getOrAwaitValue(), equalTo(false))
    }

    @Test
    fun when_updateDefaultAddress_expect_eventIsTriggered() {
        insertAddresses()
        val addressId = AddressTestUtils.getAddressList()[0].id
        viewModel.setDefaultAddress(addressId)
        verify { runTest { addressesRepository.updateDefaultAddress(addressId) } }
        assertThat(
            viewModel.defaultAddressUpdatedEvent.getOrAwaitValue().getContentIfNotHandled(),
            `is`(equalTo(Unit))
        )
    }

    @Test
    fun when_removeAddress_expect_getListWithoutRemovedAddress() {
        val removedAddressId = AddressTestUtils.getAddressList()[1].id
        insertAddresses()
        viewModel.removeAddress(removedAddressId)
        verify { runTest { addressesRepository.deleteAddressById(removedAddressId) } }

        viewModel.getAddresses()
        assertThat(
            viewModel.addressesList.getOrAwaitValue(),
            not(hasItem(AddressTestUtils.getAddressList()[1]))
        )
    }
}