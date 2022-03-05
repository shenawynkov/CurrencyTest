package com.shenawynkov.currency.presentation.converter

import androidx.lifecycle.ViewModel
import com.shenawynkov.currency.domain.usecases.ConvertRateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(private val convertRateUseCase: ConvertRateUseCase) :
    ViewModel() {
    var factor = 0.0
    val newBase = MutableStateFlow<String>("1.0")
    private val _newRate = MutableStateFlow<String>("0.0")
    val newRate: StateFlow<String> = _newRate


    fun convert() {
        val result=  newBase.value.toDoubleOrNull()?.let { convertRateUseCase(it, factor) }?:""

        _newRate.value = result.toString()

    }


}