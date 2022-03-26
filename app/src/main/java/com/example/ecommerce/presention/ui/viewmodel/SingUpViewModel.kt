package com.example.ecommerce.presention.ui.viewmodel

import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.domain.usecase.EcommerceUseCase
import com.example.ecommerce.presention.base.BaseViewModel
import com.example.ecommerce.util.RegexUtils
import com.example.ecommerce.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(private val ecommerceUseCase: EcommerceUseCase): LoginViewModel(ecommerceUseCase) {

    fun isvalidatePassword(pass: String, cfPass: String): Boolean{
        return pass.equals(cfPass)
    }

    fun singUpToApi(name: String,userName: String, pass: String) = liveData(Dispatchers.IO ) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(ecommerceUseCase.singUpToApi(name,userName, pass)))
        } catch (err: Exception) {
            emit(Resource.error(null, err.message.toString()))
        }
    }

}