package com.xavidev.testessential.utils

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.xavidev.testessential.data.repository.*
import com.xavidev.testessential.ui.addEditAddress.AddEditAddressViewModel
import com.xavidev.testessential.ui.addEditCards.AddEditCardViewModel
import com.xavidev.testessential.ui.address.AddressViewModel
import com.xavidev.testessential.ui.cart.CartViewModel
import com.xavidev.testessential.ui.intro.IntroViewModel
import com.xavidev.testessential.ui.main.PopulateViewModel
import com.xavidev.testessential.ui.paymentMethods.PaymentMethodViewModel
import com.xavidev.testessential.ui.profile.ProfileViewModel
import com.xavidev.testessential.ui.purchases.PurchasesViewModel
import com.xavidev.testessential.ui.sale.SaleViewModel
import com.xavidev.testessential.ui.search.SearchViewModel
import com.xavidev.testessential.ui.sneakerDetail.SneakerDetailDialogFragmentViewModel
import com.xavidev.testessential.ui.sneakers.BrandsViewModel
import com.xavidev.testessential.ui.sneakers.SneakersListFragmentViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val brandsRepository: BrandsRepository? = null,
    private val sneakersRepository: SneakersRepository? = null,
    private val cartRepository: CartRepository? = null,
    private val keyValueRepository: KeyValueRepository? = null,
    private val populateRepository: PopulateRepository? = null,
    private val userRepository: UserRepository? = null,
    private val purchaseRepository: PurchaseRepository? = null,
    private val addressRepository: AddressRepository? = null,
    private val cardsRepository: CardRepository? = null,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(SneakersListFragmentViewModel::class.java) -> sneakersRepository?.let {
                SneakersListFragmentViewModel(it)
            }
            isAssignableFrom(SneakerDetailDialogFragmentViewModel::class.java) -> SneakerDetailDialogFragmentViewModel(
                sneakersRepository!!,
                cartRepository!!
            )
            isAssignableFrom(CartViewModel::class.java) -> cartRepository?.let {
                CartViewModel(it)
            }
            isAssignableFrom(IntroViewModel::class.java) -> keyValueRepository?.let {
                IntroViewModel(it)
            }
            isAssignableFrom(PopulateViewModel::class.java) -> populateRepository?.let {
                PopulateViewModel(it)
            }
            isAssignableFrom(ProfileViewModel::class.java) -> userRepository?.let {
                ProfileViewModel(it)
            }
            isAssignableFrom(PurchasesViewModel::class.java) -> purchaseRepository?.let {
                PurchasesViewModel(it)
            }
            isAssignableFrom(SaleViewModel::class.java) -> SaleViewModel()
            isAssignableFrom(SearchViewModel::class.java) -> sneakersRepository?.let {
                SearchViewModel(it)
            }
            isAssignableFrom(AddressViewModel::class.java) -> addressRepository?.let {
                AddressViewModel(it)
            }
            isAssignableFrom(AddEditAddressViewModel::class.java) -> addressRepository?.let {
                AddEditAddressViewModel(it)
            }
            isAssignableFrom(BrandsViewModel::class.java) -> brandsRepository?.let {
                BrandsViewModel(it)
            }
            isAssignableFrom(PaymentMethodViewModel::class.java) -> cardsRepository?.let {
                PaymentMethodViewModel(it)
            }
            isAssignableFrom(AddEditCardViewModel::class.java) -> cardsRepository?.let {
                AddEditCardViewModel(it)
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }
    } as T
}