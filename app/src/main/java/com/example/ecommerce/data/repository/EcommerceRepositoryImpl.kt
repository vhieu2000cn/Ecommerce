package com.example.ecommerce.data.repository


import com.example.ecommerce.data.data_source.data_store.AccountDS
import com.example.ecommerce.data.data_source.local.ShoppingCartDao
import com.example.ecommerce.data.data_source.local.ShoppingCartMapperImpl
import com.example.ecommerce.data.data_source.service.EcommerceApiService
import com.example.ecommerce.domain.model.*
import com.example.ecommerce.domain.repository.EcommerceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EcommerceRepositoryImpl @Inject constructor(
    private val ecommerceService: EcommerceApiService,
    private val accountDs: AccountDS,
    private val shoppingCartDao: ShoppingCartDao,
    private val mapper: ShoppingCartMapperImpl
) : EcommerceRepository {

    override suspend fun getProductsFromApi(): Products? = ecommerceService.getProductsFromApi()
    override suspend fun saveAccount(account: String) {
        accountDs.saveAccount(account)
    }

    override suspend fun deleteAccount() {
        accountDs.deleteAccount()
    }

    override suspend fun getAccount(): Flow<String?> = accountDs.getAccount()
    override suspend fun saveToken(token: String) {
        accountDs.saveToken(token)
    }

    override suspend fun getToken(): Flow<String?> =
        accountDs.getToken()


    override suspend fun insertShoppingCart(shoppingCart: ShoppingCart) {
        shoppingCartDao.insertShoppingCart(mapper.toEntity(shoppingCart))
    }

    override fun getShopingCart(): Flow<List<ShoppingCart>> = shoppingCartDao.getShoppingCart().map {
        it.map { shoppingCartEntity ->
            mapper.fromEntity(shoppingCartEntity)
        }
    }

    override suspend fun deleteShoppingCart(id: Int) {
        shoppingCartDao.deleteShoppingCart(id)
    }

    override suspend fun deleteAllShoppingCart() {
        shoppingCartDao.deleteAllShoppingCart()
    }

    override suspend fun updateShoppingCart(shoppingCart: ShoppingCart) {
        shoppingCartDao.updatePlaylist(mapper.toEntity(shoppingCart))
    }

    override suspend fun loginToApi(email: String, pass: String): SingIn? =
        ecommerceService.login(SingInBody(email, pass))

    override suspend fun singUpToApi(name: String, email: String, pass: String): SingIn? =
        ecommerceService.singUp(SingUpBody(password = pass, email = email, name = name))

    override suspend fun orderToApi(orders: Orders,auth: String): Order? =
        ecommerceService.order(orders,auth)

}