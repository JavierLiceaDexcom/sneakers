package com.xavidev.testessential

import android.app.Application
import android.content.Context
import com.xavidev.testessential.data.repository.*
import com.xavidev.testessential.data.source.local.db.AppDatabase

class SneakersApplication : Application() {

    companion object {
        private lateinit var instance: SneakersApplication
        fun getContext(): Context = instance
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    //Repositories
    val brandsRepository: BrandsRepository get() = ServiceLocator.provideBrandsRepository(this)
    val sneakersRepository: SneakersRepository get() = ServiceLocator.provideSneakersRepository(this)
    val cartRepository: CartRepository get() = ServiceLocator.provideCartRepository(this)
    val ketValueRepository: KeyValueRepository get() = ServiceLocator.provideKeyValueRepository(this)
    val userRepository: UserRepository get() = ServiceLocator.provideUserRepository(this)
    val purchaseRepository: PurchaseRepository get() = ServiceLocator.providePurchaseRepository(this)
    val populateRepository: PopulateRepository get() = ServiceLocator.providePopulateRepository(this)
    val addressRepository: AddressRepository get() = ServiceLocator.provideAddressRepository(this)
    val cardRepository: CardRepository get() = ServiceLocator.provideCardsRepository(this)
    val database: AppDatabase? get() = ServiceLocator.database
}