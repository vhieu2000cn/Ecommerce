package com.example.ecommerce.presention.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.domain.model.SingIn
import com.example.ecommerce.domain.usecase.EcommerceUseCase
import com.example.ecommerce.presention.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val ecommerceUseCase: EcommerceUseCase
) : BaseViewModel() {

    init {
        getLoggedFromDS()
    }

    private var _logged = MutableLiveData<String?>()
    val logged: LiveData<String?> = _logged

    private var _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private var _isLogged = MutableLiveData<SingIn?>()
    val isLogged: LiveData<SingIn?> = _isLogged

    fun getLoggedFromDS() {
        viewModelScope.launch {
            ecommerceUseCase.getAccountFromDS().collect {
                _logged.value = it
                if (it != null) {
                    val email = it.split("|").toTypedArray()[0]
                    val pass = it.split("|").toTypedArray()[1]
                    try {
                        _isLogged.value = ecommerceUseCase.loginToApi(email, pass)
                    }catch (ex: Exception){
                        _error.value = "Đăng nhập thất bại"
                        ecommerceUseCase.deleteAccountFromDS()
                    }
                }
            }
        }
    }
    fun saveTokenToDs(auth: String){
        viewModelScope.launch {
            ecommerceUseCase.saveTokenToDS(auth)
        }
    }
}