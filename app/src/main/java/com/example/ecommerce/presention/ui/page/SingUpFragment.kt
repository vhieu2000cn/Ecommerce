package com.example.ecommerce.presention.ui.page

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentSingUpBinding
import com.example.ecommerce.presention.base.BaseFragment
import com.example.ecommerce.presention.ui.viewmodel.SingUpViewModel
import com.example.ecommerce.util.Status
import kotlinx.android.synthetic.main.fragment_sing_up.*

class SingUpFragment : BaseFragment<FragmentSingUpBinding, SingUpViewModel>() {
    override val layoutId: Int = R.layout.fragment_sing_up
    override val viewModel: SingUpViewModel by activityViewModels()

    override fun onReady(savedInstanceState: Bundle?) {
        initData()
    }

    private fun initData() {
        binding.btSingupBack.setOnClickListener {
            viewModel.navigateBack()
        }
        binding.edtSingupEmail.setOnFocusChangeListener { view, boolean ->
            if (!boolean) {
                if (!viewModel.isvalidate(binding.edtSingupEmail.text.toString())) {
                    binding.edtSingupEmail.error = "Not email"
                }
            }
        }

        binding.edtSingupConfirmPassword.setOnFocusChangeListener { view, boolean ->
            if (!boolean) {
                if (!viewModel.isvalidatePassword(
                        binding.edtSingupPassword.text.toString(),
                        binding.edtSingupConfirmPassword.toString()
                    )
                ) {
                    binding.edtSingupConfirmPassword.error = "Passwords do not match"
                }
            }
        }

        binding.btSingup.setOnClickListener {
            viewModel.singUpToApi(
                binding.edtSingupName.text.toString(),
                binding.edtSingupEmail.text.toString(),
                binding.edtSingupPassword.text.toString(),
            ).observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.LOADING -> {
                        binding.pbSingUp.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        binding.pbSingUp.visibility = View.GONE
                        if (it.data != null) {
                            viewModel.saveAccountToDB(binding.edtSingupEmail.text.toString() + "|" + binding.edtSingupPassword.text.toString())
                            viewModel.navigate(SingUpFragmentDirections.actionSingUpFragmentToHomeFragment())
                        }
                    }
                    Status.ERROR -> {
                        binding.pbSingUp.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            "Tạo tài khoản không thành công",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("SingUpFragment", "er "+it.message)
                    }
                }
            }
        }
    }

}