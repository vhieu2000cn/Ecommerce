package com.example.ecommerce.presention.di

import android.app.Application
import com.example.ecommerce.data.data_source.data_store.AccountDS
import com.example.ecommerce.data.data_source.local.ShoppingCartDB
import com.example.ecommerce.data.data_source.local.ShoppingCartDao
import com.example.ecommerce.data.data_source.local.ShoppingCartMapperImpl
import com.example.ecommerce.data.data_source.service.EcommerceApiConfig
import com.example.ecommerce.data.data_source.service.EcommerceApiService
import com.example.ecommerce.data.repository.EcommerceRepositoryImpl
import com.example.ecommerce.domain.repository.EcommerceRepository
import com.example.ecommerce.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.math.sin

@Module
@InstallIn(SingletonComponent::class)
object EcommerceModul {
    @Provides
    @Singleton
    fun providesShoppingCartDB(application: Application): ShoppingCartDB =
        ShoppingCartDB.getInstance(application)

    @Provides
    @Singleton
    fun providesShoppingCartDao(shoppingCartDB: ShoppingCartDB): ShoppingCartDao =
        shoppingCartDB.shoppingCartDao()

    @Provides
    @Singleton
    fun providesEcommerceApiConfig(): EcommerceApiService =
        EcommerceApiConfig.apiService.create(EcommerceApiService::class.java)

    @Provides
    @Singleton
    fun providesEcommerceRepository(
        ecommerceService: EcommerceApiService,
        accountDS: AccountDS,
        shoppingCartDao: ShoppingCartDao,
        mapper: ShoppingCartMapperImpl
    ): EcommerceRepository =
        EcommerceRepositoryImpl(ecommerceService, accountDS, shoppingCartDao, mapper)

    @Provides
    @Singleton
    fun providesEcommerceUseCase(
        ecommerceRepository: EcommerceRepository
        ): EcommerceUseCase = EcommerceUseCase(
        getProductsFromApi = GetProductsFromApi(ecommerceRepository),
        saveAccountToDS = SaveAccountToDS(ecommerceRepository),
        getAccountFromDS = GetAccountFromDS(ecommerceRepository),
        getShoppingCartFromDB = GetShoppingCartFromDB(ecommerceRepository),
        saveShoppingCartToDB = SaveShoppingCartToDB(ecommerceRepository),
        deleteShoppingCartFromDB = DeleteShoppingCartFromDB(ecommerceRepository),
        updateShoppingCartFromDB = UpdateShoppingCartFromDB(ecommerceRepository),
        loginToApi = LoginToApi(ecommerceRepository),
        singUpToApi = SingUpToApi(ecommerceRepository),
        deleteAccountFromDS = DeleteAccountFromDS(ecommerceRepository),
        deleteAllShoppingCartFromDB = DeleteAllShoppingCartFromDB(ecommerceRepository),
        orderToApi = OrderToApi(ecommerceRepository),
        saveTokenToDS = SaveTokenToDS(ecommerceRepository),
        getTokenFromDS = GetTokenFromDS(ecommerceRepository)
    )
}