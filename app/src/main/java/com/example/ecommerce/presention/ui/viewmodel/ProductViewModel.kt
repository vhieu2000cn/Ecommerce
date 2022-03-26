package com.example.ecommerce.presention.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.ecommerce.domain.model.Product
import com.example.ecommerce.domain.model.Products
import com.example.ecommerce.domain.model.ShoppingCart
import com.example.ecommerce.domain.usecase.EcommerceUseCase
import com.example.ecommerce.presention.base.BaseViewModel
import com.example.ecommerce.presention.ui.page.ProductFragmentArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val ecommerceUseCase: EcommerceUseCase) :
    BaseViewModel() {
    private val _productNumber = MutableLiveData<Int>(1)
    val productNumber: LiveData<Int> = _productNumber

    private val _product = MutableLiveData<Product?>()
    val product: MutableLiveData<Product?> = _product

    private val _shoppingCartItem = MutableLiveData<List<ShoppingCart>?>()
    val shoppingCartItem: LiveData<List<ShoppingCart>?> = _shoppingCartItem

    private fun isExistShoppingCart(): ShoppingCart? {
        _shoppingCartItem.value?.forEach {
            if (it.product.name.equals(_product.value?.name)) {
                return it
            }
        }
        return null
    }

    fun getProduct(product: Product?) {
        _product.value = product
    }

    fun getNumProduct(): Int {
        if (_product.value?.countInStock ?: 0 != 0 && isExistShoppingCart()?.qualityProduct ?: 0 < _product.value?.countInStock ?: 0) {
             _productNumber.value = 1
            return 1
        } else {
            _productNumber.value = 0
            return 0
        }
    }

    fun plusProduct(): Boolean {
        if (isExistShoppingCart()?.qualityProduct != 0 && isExistShoppingCart() != null) {
            if (_product.value?.countInStock ?: 0 > ((_productNumber.value
                    ?: 0) + isExistShoppingCart()!!.qualityProduct)
            ) {
                _productNumber.value = _productNumber.value?.plus(1)
                return true
            } else {
                return false
            }
        }
        if (_product.value?.countInStock ?: 0 > _productNumber.value ?: 0) {
            _productNumber.value = _productNumber.value?.plus(1)
            return true
        } else {
            return false
        }
    }

    fun minusProduct(): Boolean {
        if (_productNumber.value ?: 1 > 1) {
            _productNumber.value = _productNumber.value?.minus(1)
            return true
        }
        return false
    }

    fun insertShoppingCartToDB(shoppingCart: ShoppingCart?) {
        if (shoppingCart != null && getNumProduct() !=0) {
            if (_shoppingCartItem.value?.isNotEmpty() == true && isExistShoppingCart()?.qualityProduct != 0 && isExistShoppingCart() != null) {
                viewModelScope.launch {
                    ecommerceUseCase.updateShoppingCartFromDB(
                        ShoppingCart(
                            isExistShoppingCart()!!.id,
                            isExistShoppingCart()!!.qualityProduct + shoppingCart.qualityProduct,
                            isExistShoppingCart()!!.product
                        )
                    )
                }
            } else {
                viewModelScope.launch {
                    shoppingCart?.let { ecommerceUseCase.saveShoppingCartToDB(it) }
                }
            }
        }
    }

    fun getShoppingCart() {
        viewModelScope.launch {
            ecommerceUseCase.getShoppingCartFromDB().collect {
                _shoppingCartItem.value = it
            }
        }
    }
}