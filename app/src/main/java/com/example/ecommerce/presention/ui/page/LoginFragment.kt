package com.example.ecommerce.presention.ui.page

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentLoginBinding
import com.example.ecommerce.presention.base.BaseFragment
import com.example.ecommerce.presention.ui.viewmodel.LoginViewModel
import com.example.ecommerce.util.Status


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val layoutId: Int = R.layout.fragment_login
    override val viewModel: LoginViewModel by activityViewModels()

    override fun onReady(savedInstanceState: Bundle?) {
        binding.edtLoginEmail.setOnFocusChangeListener { view, boolean ->
            if (!boolean) {
                if (!viewModel.isvalidate(binding.edtLoginEmail.text.toString())) {
                    binding.edtLoginEmail.setError("Not email")
                }
            }
        }
        binding.btSingin.setOnClickListener {
            viewModel.loginToApi(
                binding.edtLoginEmail.text.toString(),
                binding.edtLoginPassword.text.toString()
            ).observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.LOADING -> {
                        binding.pbLogin.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        binding.pbLogin.visibility = View.GONE
                        if (it.data != null) {
                            viewModel.saveTokenToDs(it.data.token!!)
                            viewModel.saveAccountToDB(binding.edtLoginEmail.text.toString() + "|" + binding.edtLoginPassword.text.toString())
                            viewModel.navigateBack()
                        }
                    }
                    Status.ERROR -> {
                        binding.pbLogin.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            "Tài khoản hoặc mật khẩu không đúng",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        binding.btSingup.setOnClickListener {
            viewModel.navigate(LoginFragmentDirections.actionLoginFragmentToSingUpFragment())
        }
    }
}
