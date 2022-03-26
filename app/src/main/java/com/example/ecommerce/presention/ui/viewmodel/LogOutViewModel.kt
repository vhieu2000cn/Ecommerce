package com.example.ecommerce.presention.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.ecommerce.domain.usecase.EcommerceUseCase
import com.example.ecommerce.presention.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogOutViewModel @Inject constructor(private val ecommerceUseCase: EcommerceUseCase) :
    BaseViewModel() {
    fun deleteAccountFromDB() {
        viewModelScope.launch {
            ecommerceUseCase.deleteAccountFromDS()
        }
    }
    fun deleteSpcFromDb(){
        viewModelScope.launch {
            ecommerceUseCase.deleteAllShoppingCartFromDB()
        }
    }
}