package com.xavidev.testessential.ui.profile

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.R
import com.xavidev.testessential.data.State
import com.xavidev.testessential.data.db.DatabaseBuilder
import com.xavidev.testessential.data.entity.User
import com.xavidev.testessential.repository.UserRepository
import com.xavidev.testessential.resources.UserResources
import com.xavidev.testessential.utils.NavigationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository) : NavigationViewModel() {

    fun clickAddressInfo(view: View) {
        Log.i("JAVI", "KK")
        navigateTo(view, R.id.action_profileFragment_to_addressesFragment)
    }

    fun clickPaymentMethods(view: View) {
        navigateTo(view, R.id.action_profileFragment_to_paymentMethodsFragment)
    }

    fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> {}
                    State.ERROR -> {}
                }
            }
    }

    fun getUser(userId: String) = viewModelScope.launch {
        userRepository.getUser(userId).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> {}
                    State.ERROR -> {}
                }
            }
    }

    fun updateUser(user: User) = viewModelScope.launch {
        userRepository.updateUser(user).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> {}
                    State.SUCCESS -> {}
                    State.ERROR -> {}
                }
            }
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
                return ProfileViewModel(
                    UserResources(DatabaseBuilder.instance.database.userDao())
                ) as T
            }
            throw Exception("Class not supported")
        }
    }
}