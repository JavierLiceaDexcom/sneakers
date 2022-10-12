package com.xavidev.testessential.data.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.Result.Error
import com.xavidev.testessential.data.Result.Success
import com.xavidev.testessential.data.repository.AddressRepository
import com.xavidev.testessential.data.source.local.entity.Address

class AddressRepositoryFake : AddressRepository {

    private val addressServiceData: LinkedHashMap<String, Address> = LinkedHashMap()
    private val observableAddress = MutableLiveData<Result<List<Address>>>()

    override suspend fun insertAddress(address: Address): Result<Unit> {
        return try {
            addressServiceData[address.id] = address
            Success(Unit)
        } catch (ex: Exception) {
            Error(Exception(ex.localizedMessage))
        }
    }

    override fun observeAddresses(): LiveData<Result<List<Address>>> {
        return observableAddress
    }

    override suspend fun getAllAddresses(): Result<List<Address>> {
        return try {
            Success(addressServiceData.values.toList())
        } catch (ex: Exception) {
            Error(Exception(ex.localizedMessage))
        }
    }

    override suspend fun getDefaultAddress(): Result<Address?> {
        TODO("Not yet implemented")
    }

    override suspend fun getAddressById(addressId: String): Result<Address> {
        return try {
            val address = addressServiceData.getValue(addressId)
            Success(address)
        } catch (ex: Exception) {
            Error(ex)
        }
    }

    override fun observeAddressById(addressId: String): LiveData<Result<Address>> {
        return try {
            MutableLiveData(Success(addressServiceData.getValue(addressId)))
        } catch (ex: Exception) {
            MutableLiveData(Error(ex))
        }
    }

    override suspend fun updateAddress(address: Address): Result<Int> {
        return try {
            addressServiceData[address.id] = address
            Success(1)
        } catch (ex: Exception) {
            Error(ex)
        }
    }

    override suspend fun updateDefaultAddress(addressId: String): Result<Unit> {
        return try {
            Success(Unit)
        } catch (ex: Exception) {
            Error(ex)
        }
    }

    override suspend fun deleteAddress(address: Address) {
        addressServiceData.remove(address.id)
    }

    override suspend fun deleteAddressById(addressId: String): Result<Int> {
        return try {
            addressServiceData.remove(addressId)
            Success(1)
        } catch (ex: Exception) {
            Error(ex)
        }
    }

    override suspend fun refreshAddresses() {
        observableAddress.value = getAllAddresses()
    }

    override suspend fun refreshAddress(addressId: String) {
        TODO("Not yet implemented")
    }
}