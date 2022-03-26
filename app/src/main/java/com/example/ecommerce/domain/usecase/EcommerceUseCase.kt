package com.example.ecommerce.domain.usecase

data class EcommerceUseCase(
    val getProductsFromApi: GetProductsFromApi,
    val saveAccountToDS:SaveAccountToDS,
    val getAccountFromDS: GetAccountFromDS,
    val getShoppingCartFromDB: GetShoppingCartFromDB,
    val saveShoppingCartToDB: SaveShoppingCartToDB,
    val deleteShoppingCartFromDB: DeleteShoppingCartFromDB,
    val updateShoppingCartFromDB: UpdateShoppingCartFromDB,
    val loginToApi: LoginToApi,
    val singUpToApi: SingUpToApi,
    val deleteAccountFromDS: DeleteAccountFromDS,
    val deleteAllShoppingCartFromDB: DeleteAllShoppingCartFromDB,
    val orderToApi: OrderToApi,
    val saveTokenToDS: SaveTokenToDS,
    val getTokenFromDS: GetTokenFromDS
)
