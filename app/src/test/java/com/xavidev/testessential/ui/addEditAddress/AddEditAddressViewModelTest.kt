package com.xavidev.testessential.ui.addEditAddress

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xavidev.testessential.MainCoroutineRule
import com.xavidev.testessential.R
import com.xavidev.testessential.data.source.repository.AddressRepositoryFake
import com.xavidev.testessential.getOrAwaitValue
import com.xavidev.testessential.utils.AddressTestUtils
import io.mockk.junit4.MockKRule
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
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

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: AddEditAddressViewModel

    private val addressRepository = spyk<AddressRepositoryFake>()

    @Before
    fun setUp() {
        viewModel = AddEditAddressViewModel(addressRepository)
    }

    @Test
    fun when_addressIdIsNull_expect_prepareToCreateNewAddress() {
        viewModel.start(null)
        val isNewAddressField = viewModel::class.java.getDeclaredField("_isNewAddress")
        isNewAddressField.isAccessible = true
        val isNewAddressValue = isNewAddressField.get(viewModel) as Boolean

        assertThat(isNewAddressValue, `is`(equalTo(true)))
        assertThat(viewModel.actionText.getOrAwaitValue(), `is`(equalTo(R.string.text_save_address)))
    }

    @Test
    fun when_addressIdIsNotNull_expect_prepareToUpdateAddress() {
        viewModel.start(UUID.randomUUID().toString())
        val isNewAddressField = viewModel::class.java.getDeclaredField("_isNewAddress")
        isNewAddressField.isAccessible = true
        val isNewAddressValue = isNewAddressField.get(viewModel) as Boolean

        assertThat(isNewAddressValue, `is`(equalTo(false)))
        assertThat(viewModel.actionText.getOrAwaitValue(), `is`(equalTo(R.string.text_update_address)))
    }

    @Test
    fun when_addressIdIsNotNull_expect_getAddressFromDb() = runTest{
        val address = AddressTestUtils.getSingleAddress()
        addressRepository.insertAddress(address)

        viewModel.start(address.id)
        verify(exactly = 1) { addressRepository.observeAddressById(address.id) }
        assertThat(viewModel.address.getOrAwaitValue(), `is`(equalTo(address)))
    }

    @Test
    fun when_saveAddress_expected_insertAddressToDbAndGetSuccessMessage() {
        val address = AddressTestUtils.getSingleAddress()
        viewModel.start(null)
        viewModel.saveOrUpdateAddress(address)
        verify { runTest { addressRepository.insertAddress(address) } }
        assertThat(viewModel.addressSavedMessage.getOrAwaitValue().peekContent(), `is`(equalTo(R.string.text_address_saved)))
    }

    @Test
    fun when_updateAddress_expected_updateAddressInDbAndGetSuccessMessage() {
        val address = AddressTestUtils.getSingleAddress()
        viewModel.start(address.id)
        viewModel.saveOrUpdateAddress(address)
        verify { runTest { addressRepository.updateAddress(address) } }
        assertThat(viewModel.addressSavedMessage.getOrAwaitValue().peekContent(), `is`(equalTo(R.string.text_address_updated)))
    }
}