package com.example.ecommerce.domain.usecase

import com.example.ecommerce.domain.model.Order
import com.example.ecommerce.domain.model.Orders
import com.example.ecommerce.domain.model.SingIn
import com.example.ecommerce.domain.repository.EcommerceRepository
import javax.inject.Inject

class OrderToApi @Inject constructor(private val ecommerceRepository: EcommerceRepository) {
    suspend operator fun invoke(orders: Orders,auth: String): Order? = ecommerceRepository.orderToApi(orders,auth)
}