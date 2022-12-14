package com.xavidev.testessential.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.xavidev.testessential.data.source.local.entity.Address

@Dao
interface AddressDao {
    //TODO: Add soft delete logic

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: Address)

    @Query("SELECT * FROM address")
    fun observeAddresses(): LiveData<List<Address>>

    @Query("SELECT * FROM address")
    fun getAllAddresses(): List<Address>

    @Query("SELECT * FROM address WHERE id = :addressId")
    suspend fun getAddressById(addressId: String): Address?

    @Query("SELECT * FROM address WHERE id = :addressId")
    fun observeAddressById(addressId: String): LiveData<Address>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAddress(address: Address): Int

    @Query("UPDATE address SET is_default = :value WHERE id = :addressId")
    suspend fun setDefaultAddress(addressId: String, value: Boolean)

    @Query("SELECT * FROM address WHERE is_default = :value")
    suspend fun getDefaultAddress(value: Boolean = true): Address?

    @Transaction
    suspend fun updateDefaultAddress(newAddressId: String){
        val oldAddressId = getDefaultAddress()?.id
        if (newAddressId == oldAddressId) return
        oldAddressId?.let { setDefaultAddress(it, false) }
        setDefaultAddress(newAddressId, true)
    }

    @Delete
    suspend fun deleteAddress(address: Address)

    @Query("DELETE FROM address WHERE id = :addressId")
    suspend fun deleteAddressById(addressId: String): Int
}