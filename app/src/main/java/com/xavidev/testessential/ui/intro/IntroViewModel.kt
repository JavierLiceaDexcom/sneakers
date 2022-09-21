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
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class IntroViewModel(private val keyValueRepository: KeyValueRepository) : ViewModel() {

    private val _introPassedLoader = MutableLiveData(false)
    val introPassedLoader: LiveData<Boolean> get() = _introPassedLoader

    private val _introPassed = MutableLiveData(false)
    val introPassed: LiveData<Boolean> get() = _introPassed

    private fun saveKeyValue(keyValue: KeyValue) = viewModelScope.launch {
        keyValueRepository.insertKeyValue(keyValue).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response) {
                    is Result.Loading -> _introPassedLoader.postValue(true)
                    is Result.Success -> {
                        _introPassedLoader.postValue(false)
                        _introPassed.postValue(true)
                    }
                    is Result.Error -> {
                        _introPassedLoader.postValue(false)
                        _introPassed.postValue(false)
                    }
                }
            }
    }

    fun setIntroPassed() {
        val keyValue = KeyValue(KeyValueDao.INTRO_PASSED, KeyValueDao.TRUE_VALUE)
        saveKeyValue(keyValue)
    }
}