package com.xavidev.testessential

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.xavidev.testessential.data.repository.*
import com.xavidev.testessential.data.resources.*
import com.xavidev.testessential.data.source.local.db.AppDatabase
import com.xavidev.testessential.data.source.local.db.DB_NAME

object ServiceLocator {

    private var database: AppDatabase? = null

    @VisibleForTesting
    @Volatile
    var brandsRepository: BrandsRepository? = null
        @VisibleForTesting set

    @VisibleForTesting
    @Volatile
    var sneakersRepository: SneakersRepository? = null
        @VisibleForTesting set

    @VisibleForTesting
    @Volatile
    var cartRepository: CartRepository? = null
        @VisibleForTesting set

    @VisibleForTesting
    @Volatile
    var keyValueRepository: KeyValueRepository? = null
        @VisibleForTesting set

    @VisibleForTesting
    @Volatile
    var userRepository: UserRepository? = null
        @VisibleForTesting set

    @VisibleForTesting
    @Volatile
    var purchaseRepository: PurchaseRepository? = null
        @VisibleForTesting set

    @VisibleForTesting
    @Volatile
    var populateRepository: PopulateRepository? = null
        @VisibleForTesting set

    @VisibleForTesting
    @Volatile
    var addressRepository: AddressRepository? = null
        @VisibleForTesting set

    private val lock = Any()

    private fun createDatabase(context: Context): AppDatabase {
        val result = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
        database = result
        return result
    }

    private fun getDatabase(context: Context): AppDatabase = database ?: createDatabase(context)

    //Brands
    fun provideBrandsRepository(context: Context): BrandsRepository {
        synchronized(lock) {
            return brandsRepository ?: createBrandsRepository(context)
        }
    }

    private fun createBrandsRepository(context: Context): BrandsRepository {
        val newRepo = BrandsResources(getDatabase(context).brandsDao())
        brandsRepository = newRepo
        return newRepo
    }

    //Sneakers
    fun provideSneakersRepository(context: Context): SneakersRepository {
        synchronized(lock) {
            return sneakersRepository ?: createSneakersRepository(context)
        }
    }

    private fun createSneakersRepository(context: Context): SneakersRepository {
        val newRepo = SneakersResources(getDatabase(context).sneakerDao())
        sneakersRepository = newRepo
        return newRepo
    }

    //Cart
    fun provideCartRepository(context: Context): CartRepository {
        synchronized(lock) {
            return cartRepository ?: createCartRepository(context)
        }
    }

    private fun createCartRepository(context: Context): CartRepository {
        val newRepo = CartResources(getDatabase(context).cartDao())
        cartRepository = newRepo
        return newRepo
    }

    //KeyValue
    fun provideKeyValueRepository(context: Context): KeyValueRepository {
        synchronized(lock) {
            return keyValueRepository ?: createKeyValueRepository(context)
        }
    }

    private fun createKeyValueRepository(context: Context): KeyValueRepository {
        val newRepo = KeyValueResource(getDatabase(context).keyValueDao())
        keyValueRepository = newRepo
        return newRepo
    }

    //User
    fun provideUserRepository(context: Context): UserRepository {
        synchronized(lock) {
            return userRepository ?: createUserRepository(context)
        }
    }

    private fun createUserRepository(context: Context): UserRepository {
        val newRepo = UserResources(getDatabase(context).userDao())
        userRepository = newRepo
        return newRepo
    }

    //Purchase
    fun providePurchaseRepository(context: Context): PurchaseRepository {
        synchronized(lock) {
            return purchaseRepository ?: createPurchaseRepository(context)
        }
    }

    private fun createPurchaseRepository(context: Context): PurchaseRepository {
        val newRepo = PurchaseResources(getDatabase(context).purchasesDao())
        purchaseRepository = newRepo
        return newRepo
    }

    //Populate
    fun providePopulateRepository(context: Context): PopulateRepository {
        synchronized(lock) {
            return populateRepository ?: createPopulateRepository(context)
        }
    }

    private fun createPopulateRepository(context: Context): PopulateRepository {
        val db = getDatabase(context)
        val newRepo = PopulateResources(
            db.brandsDao(),
            db.sneakerDao(),
            db.imagesDao()
        )
        populateRepository = newRepo
        return newRepo
    }

    //Address
    fun provideAddressRepository(context: Context): AddressRepository {
        synchronized(lock) {
            return addressRepository ?: createAddressRepository(context)
        }
    }

    private fun createAddressRepository(context: Context): AddressRepository {
        val newRepo = AddressResources(getDatabase(context).addressDao())
        addressRepository = newRepo
        return newRepo
    }
}
