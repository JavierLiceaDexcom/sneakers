package com.xavidev.testessential.ui.intro

import androidx.lifecycle.*
import com.xavidev.testessential.data.State
import com.xavidev.testessential.data.dao.KeyValueDao
import com.xavidev.testessential.data.db.DatabaseBuilder
import com.xavidev.testessential.data.entity.KeyValue
import com.xavidev.testessential.repository.KeyValueRepository
import com.xavidev.testessential.resources.KeyValueResource
import com.xavidev.testessential.utils.App
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
                when (response.status!!) {
                    State.LOADING -> _introPassedLoader.postValue(true)
                    State.SUCCESS -> {
                        _introPassedLoader.postValue(false)
                        _introPassed.postValue(true)
                    }
                    State.ERROR -> {
                        _introPassedLoader.postValue(false)
                        _introPassed.postValue(false)
                    }
                }
            }
    }

    fun getKeyValue(key: String) = viewModelScope.launch {
        keyValueRepository.getKeyValue(key).flowOn(Dispatchers.IO)
            .collect { response ->
                when (response.status!!) {
                    State.LOADING -> _introPassedLoader.postValue(true)
                    State.SUCCESS -> {
                        val result = response.data?.value.toBoolean()
                        _introPassedLoader.postValue(false)
                        _introPassed.postValue(result)
                    }
                    State.ERROR -> {
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

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(IntroViewModel::class.java)) {
                return IntroViewModel(
                    KeyValueResource(
                        DatabaseBuilder.instance.database.keyValueDao()
                    )
                ) as T
            }
            throw Exception("No class supported")
        }
    }
}