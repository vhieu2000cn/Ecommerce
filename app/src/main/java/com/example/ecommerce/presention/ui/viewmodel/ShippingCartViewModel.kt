package com.example.ecommerce.presention.ui.viewmodel

import com.example.ecommerce.presention.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShippingCartViewModel @Inject constructor(): BaseViewModel() {
    fun isvalidate(str: String): Boolean{
        return str.isNotEmpty()
    }
    fun isPostalcode(str: String): Boolean{
        return str.length == 6
    }
}