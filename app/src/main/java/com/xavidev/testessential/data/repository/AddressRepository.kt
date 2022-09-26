package com.xavidev.testessential.data.repository

import androidx.lifecycle.LiveData
import com.xavidev.testessential.data.source.local.entity.Address
import com.xavidev.testessential.data.Result

interface AddressRepository {
    suspend fun insertAddress(address: Address): Result<Unit>

    fun observeAddresses(): LiveData<Result<List<Address>>>

    suspend fun getAllAddresses(): Result<List<Address>>

    suspend fun getAddressById(addressId: String): Result<Address>

    fun observeAddressById(addressId: String): LiveData<Result<Address>>

    suspend fun updateAddress(address: Address): Result<Int>

    suspend fun updateDefaultAddress(addressId: String): Result<Unit>

    suspend fun deleteAddress(address: Address)

    suspend fun deleteAddressById(addressId: String): Result<Int>

    suspend fun refreshAddresses()

    suspend fun refreshAddress(addressId: String)
}