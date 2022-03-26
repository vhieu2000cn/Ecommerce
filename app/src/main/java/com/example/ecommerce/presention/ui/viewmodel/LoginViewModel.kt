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
open class LoginViewModel @Inject constructor(private val ecommerceUseCase: EcommerceUseCase) :
    BaseViewModel() {



    fun loginToApi(userName: String, pass: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(ecommerceUseCase.loginToApi(userName, pass)))
        } catch (err: Exception) {
            emit(Resource.error(null, err.message.toString()))
        }
    }

    fun isvalidate(email: String): Boolean {
        return RegexUtils.isValidEmail(email)
    }

    fun saveAccountToDB(account: String) {
        viewModelScope.launch {
            ecommerceUseCase.saveAccountToDS(account)
        }
    }
    fun saveTokenToDs(auth: String){
        viewModelScope.launch {
            ecommerceUseCase.saveTokenToDS(auth)
        }
    }
}