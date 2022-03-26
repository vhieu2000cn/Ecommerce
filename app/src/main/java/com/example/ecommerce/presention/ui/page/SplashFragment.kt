package com.example.ecommerce.presention.ui.page

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentSplashBinding
import com.example.ecommerce.presention.base.BaseFragment
import com.example.ecommerce.presention.ui.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override val layoutId: Int = R.layout.fragment_splash
    override val viewModel: SplashViewModel by activityViewModels()

    override fun onReady(savedInstanceState: Bundle?) {

        viewModel.logged.observe(viewLifecycleOwner){
            if (it == null){
                viewModel.navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
        viewModel.isLogged.observe(viewLifecycleOwner){
            if (it != null){
                viewModel.saveTokenToDs(it.token!!)
                viewModel.navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
        viewModel.error.observe(viewLifecycleOwner){
            if (it.equals("Đăng nhập thất bại")){
                viewModel.navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
                Toast.makeText(requireContext(),"Đăng nhập thất bại",Toast.LENGTH_SHORT).show()
            }
        }
    }


}