package com.xavidev.testessential.ui.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xavidev.testessential.data.Result
import com.xavidev.testessential.data.repository.KeyValueRepository
import com.xavidev.testessential.data.source.local.dao.KeyValueDao
import com.xavidev.testessential.data.source.local.entity.KeyValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class IntroViewModel(private val keyValueRepository: KeyValueRepository) : ViewModel() {

    private val _introPassed = MutableLiveData(false)
    val introPassed: LiveData<Boolean> get() = _introPassed

    private fun saveKeyValue(keyValue: KeyValue) = viewModelScope.launch {
        keyValueRepository.insertKeyValue(keyValue).flowOn(Dispatchers.IO).collect()
    }

    fun getIntroPassed() = viewModelScope.launch {
        keyValueRepository.getKeyValue(KeyValueDao.INTRO_PASSED).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        val passed = response.data.value.toBoolean()
                        _introPassed.postValue(passed)
                    }
                    is Result.Error -> {}
                }
            }
    }

    fun setIntroPassed() {
        val keyValue = KeyValue(KeyValueDao.INTRO_PASSED, KeyValueDao.TRUE_VALUE)
        saveKeyValue(keyValue)
    }
}