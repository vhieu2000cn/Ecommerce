package com.example.ecommerce.presention.ui.page

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentLogOutBinding
import com.example.ecommerce.presention.base.BaseFragment
import com.example.ecommerce.presention.ui.viewmodel.LogOutViewModel
import com.example.ecommerce.presention.ui.viewmodel.SplashViewModel

class LogOutFragment : BaseFragment<FragmentLogOutBinding, LogOutViewModel>() {
    override val layoutId: Int = R.layout.fragment_log_out
    override val viewModel: LogOutViewModel by activityViewModels()

    override fun onReady(savedInstanceState: Bundle?) {
        binding.btLogout.setOnClickListener {
            viewModel.deleteAccountFromDB()
            viewModel.deleteSpcFromDb()
            viewModel.navigate(LogOutFragmentDirections.actionLogOutFragmentToSplashFragment())
        }
    }

}