package com.example.ecommerce.presention.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.domain.model.Product
import com.example.ecommerce.domain.model.Products
import com.example.ecommerce.domain.usecase.EcommerceUseCase
import com.example.ecommerce.presention.base.BaseViewModel
import com.example.ecommerce.presention.ui.adapter.ListProductAdapter
import com.example.ecommerce.presention.ui.page.HomeFragmentDirections
import com.example.ecommerce.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val ecommerceUseCase: EcommerceUseCase
) : BaseViewModel(), ListProductAdapter.IListenClick {

    private val _products = MutableLiveData<Resource<Products>>()
    val products: LiveData<Resource<Products>> = _products

    //
    val handler = CoroutineExceptionHandler { _, exception ->
        _products.value = Resource.error(null, exception.message.toString())
    }

    fun getProductsFromApi() {
        viewModelScope.launch(handler) {
            val job = CoroutineScope(Dispatchers.IO).async {
                ecommerceUseCase.getProductsFromApi()
            }
            _products.value = Resource.success(job.await()!!)
        }
    }

    override fun listenClick(product: Product) {
        navigate(HomeFragmentDirections.actionHomeFragmentToProductFragment(product))
    }

}