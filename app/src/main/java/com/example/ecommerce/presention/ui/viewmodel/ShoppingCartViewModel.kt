package com.example.ecommerce.presention.ui.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.domain.model.Product
import com.example.ecommerce.domain.model.ShoppingCart
import com.example.ecommerce.domain.usecase.EcommerceUseCase
import com.example.ecommerce.presention.base.BaseViewModel
import com.example.ecommerce.presention.ui.adapter.ListShoppingCartAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(private val ecommerceUseCase: EcommerceUseCase) :
    BaseViewModel(),ListShoppingCartAdapter.IListenClick {
    private val _shoppingCartItem = MutableLiveData<List<ShoppingCart>?>()
    val shoppingCartItem: LiveData<List<ShoppingCart>?> = _shoppingCartItem

    private val _totalPrice =  MutableLiveData<Double>(0.0)
    val totalPrice: LiveData<Double> = _totalPrice


    fun getShoppingCart(){
        viewModelScope.launch {
            ecommerceUseCase.getShoppingCartFromDB().collect {
                _shoppingCartItem.value = it
                getTotalPrice()
            }
        }
    }
    fun getTotalPrice(){
        _totalPrice.value = 0.0
        _shoppingCartItem.value?.map {
            _totalPrice.value = ceil(((_totalPrice.value?:0.0) + (it.qualityProduct * it.product.price))*10000) /10000
        }
    }
    override fun minusShoppingCart(shoppingCart: ShoppingCart) {
        if (shoppingCart.qualityProduct == 1 && shoppingCart.id != null){
            viewModelScope.launch {
                ecommerceUseCase.deleteShoppingCartFromDB(shoppingCart.id!!)
            }
            getTotalPrice()
        }else{
            viewModelScope.launch {
                ecommerceUseCase.updateShoppingCartFromDB(
                    ShoppingCart(
                        shoppingCart.id,
                        shoppingCart.qualityProduct-1,
                        shoppingCart.product
                    )
                )
            }
            getTotalPrice()
        }

    }

    override fun plusShoppingCart(shoppingCart: ShoppingCart): Boolean {
        if (shoppingCart.qualityProduct < shoppingCart.product.countInStock){
            viewModelScope.launch {
                ecommerceUseCase.updateShoppingCartFromDB(
                    ShoppingCart(
                        shoppingCart.id,
                        shoppingCart.qualityProduct+1,
                        shoppingCart.product
                    )
                )
            }
            return true
        }
        else return false
    }

    override fun cancelShoppingCart(shoppingCart: ShoppingCart) {
        viewModelScope.launch {
            shoppingCart.id?.let { ecommerceUseCase.deleteShoppingCartFromDB(it) }
        }
    }
}