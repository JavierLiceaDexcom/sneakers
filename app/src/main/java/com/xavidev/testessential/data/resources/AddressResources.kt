package com.xavidev.testessential.data.resources

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.Result.Error
import com.xavidev.testessential.data.Result.Success
import com.xavidev.testessential.data.repository.AddressRepository
import com.xavidev.testessential.data.source.local.dao.AddressDao
import com.xavidev.testessential.data.source.local.entity.Address
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class AddressResources internal constructor(
    private val addressDao: AddressDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AddressRepository {

    override suspend fun insertAddress(address: Address): Result<Unit> = withContext(ioDispatcher) {
        val existsDefault = addressDao.getDefaultAddress()
        return@withContext try {
            val inserted = addressDao.insertAddress(address)
            if (existsDefault == null)
                addressDao.updateDefaultAddress(address.id)
            Success(inserted)
        } catch (ex: Exception) {
            Error(ex)
        }
    }

    override fun observeAddresses(): LiveData<Result<List<Address>>> {
        return addressDao.observeAddresses().map { Success(it) }
    }

    override suspend fun getAllAddresses(): Result<List<Address>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(addressDao.getAllAddresses())
        } catch (ex: Exception) {
            Error(ex)
        }
    }

    override suspend fun getDefaultAddress(): Result<Address?> = withContext(ioDispatcher) {
        return@withContext try {
            Success(addressDao.getDefaultAddress())
        } catch (ex: Exception) {
            Error(ex)
        }
    }

    override suspend fun getAddressById(addressId: String): Result<Address> =
        withContext(ioDispatcher) {
            try {
                val address = addressDao.getAddressById(addressId)
                if (address != null) {
                    return@withContext Success(address)
                } else {
                    return@withContext Error(Exception("Address not found"))
                }
            } catch (ex: Exception) {
                return@withContext Error(ex)
            }
        }

    override fun observeAddressById(addressId: String): LiveData<Result<Address>> =
        addressDao.observeAddressById(addressId).map { Success(it) }

    override suspend fun updateAddress(address: Address): Result<Int> = withContext(ioDispatcher) {
        return@withContext try {
            Success(addressDao.updateAddress(address))
        } catch (ex: Exception) {
            Error(ex)
        }
    }

    override suspend fun updateDefaultAddress(addressId: String) = withContext(ioDispatcher) {
        return@withContext try {
            Success(addressDao.updateDefaultAddress(addressId))
        } catch (ex: Exception) {
            Error(ex)
        }
    }

    override suspend fun deleteAddress(address: Address) {
        coroutineScope { addressDao.deleteAddress(address) }
    }

    override suspend fun deleteAddressById(addressId: String): Result<Int> =
        withContext(ioDispatcher) {
            return@withContext try {
                Success(addressDao.deleteAddressById(addressId))
            } catch (ex: Exception) {
                Error(ex)
            }
        }

    override suspend fun refreshAddresses() {
        //NO-OP
    }

    override suspend fun refreshAddress(addressId: String) {
        //NO-OP
    }
}