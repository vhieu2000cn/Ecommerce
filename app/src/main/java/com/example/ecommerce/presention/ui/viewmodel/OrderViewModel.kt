package com.example.ecommerce.presention.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.domain.model.Orders
import com.example.ecommerce.domain.usecase.EcommerceUseCase
import com.example.ecommerce.presention.base.BaseViewModel
import com.example.ecommerce.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val ecommerceUseCase: EcommerceUseCase): BaseViewModel() {
    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token
    init {
        getTokenFromDS()
    }


    fun orderToApi(orders: Orders,auth: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(ecommerceUseCase.orderToApi(orders,auth)))
        } catch (err: Exception) {
            emit(Resource.error(null, err.message.toString()))
        }
    }
    fun getTokenFromDS(){
        viewModelScope.launch {
            ecommerceUseCase.getTokenFromDS().collect{
                _token.value = it
            }
        }
    }
}