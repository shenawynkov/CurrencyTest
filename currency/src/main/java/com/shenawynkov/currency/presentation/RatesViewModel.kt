package com.shenawynkov.currency.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shenawynkov.currency.Resource
import com.shenawynkov.currency.domain.usecases.GetRatesUseCase
import com.shenawynkov.currency.domain.model.Rate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
@HiltViewModel
class RatesViewModel @Inject constructor(private val getRatesUseCase: GetRatesUseCase):ViewModel() {
    private val _rates = MutableStateFlow<List<Rate>>(ArrayList())
    val rates: StateFlow<List<Rate>> = _rates
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading
    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    init {
        getRatesUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _loading.value = false
                    result.data?.let {
                        _rates.value = it
                    }

                }
                is Resource.Error -> {
                    _loading.value = false
                    _message.value = result.message
                    result.data?.let {
                        _rates.value = it
                    }
                }
                is Resource.Loading -> {
                    _loading.value = true

                }
            }
        }.launchIn(viewModelScope)
    }
}