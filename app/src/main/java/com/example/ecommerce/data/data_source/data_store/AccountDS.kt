package com.example.ecommerce.data.data_source.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


const val MY_ACCOUNT = "MY_ACCOUNT"


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = MY_ACCOUNT)

class AccountDS @Inject constructor(@ApplicationContext private val context: Context) {
    companion object{
        val nameAccount = stringPreferencesKey("Name")
        val tokenAccount = stringPreferencesKey("TOKEN")
    }
    suspend fun saveAccount(account: String){
        context.dataStore.edit { accounts->
            accounts[nameAccount] = account
        }
    }
    suspend fun saveToken(token:String){
        context.dataStore.edit { accounts->
            accounts[tokenAccount] = token
        }
    }
    suspend fun deleteAccount(){
        context.dataStore.edit { accounts->
            accounts.clear()
        }
    }
    fun getAccount():Flow<String?> = context.dataStore.data.map { account->
        account[nameAccount]
    }
    fun getToken():Flow<String?> = context.dataStore.data.map { account->
        account[tokenAccount]
    }
}
//