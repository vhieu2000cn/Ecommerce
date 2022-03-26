package com.example.ecommerce.presention.ui.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentShippingCartBinding
import com.example.ecommerce.domain.model.Orders
import com.example.ecommerce.domain.model.ShippingAddress
import com.example.ecommerce.presention.base.BaseFragment
import com.example.ecommerce.presention.ui.viewmodel.ShippingCartViewModel


class ShippingCartFragment : BaseFragment<FragmentShippingCartBinding,ShippingCartViewModel>() {
    override val layoutId: Int = R.layout.fragment_shipping_cart
    override val viewModel: ShippingCartViewModel by activityViewModels()

    private val navArgs: ShippingCartFragmentArgs by navArgs()

    override fun onReady(savedInstanceState: Bundle?) {
        binding.edtShippingAddress.setOnFocusChangeListener { view, boolean ->
            if (!boolean) {
                if (!viewModel.isvalidate(binding.edtShippingAddress.text.toString())) {
                    binding.edtShippingAddress.setError("require")
                }
            }
        }
        binding.edtShippingCity.setOnFocusChangeListener { view, boolean ->
            if (!boolean) {
                if (!viewModel.isvalidate(binding.edtShippingCity.text.toString())) {
                    binding.edtShippingCity.setError("require")
                }
            }
        }
        binding.edtShippingCountry.setOnFocusChangeListener { view, boolean ->
            if (!boolean) {
                if (!viewModel.isvalidate(binding.edtShippingCountry.text.toString())) {
                    binding.edtShippingCountry.setError("require")
                }
            }
        }
        binding.edtShippingPostalcode.setOnFocusChangeListener { view, boolean ->
            if (!boolean) {
                if (!viewModel.isPostalcode(binding.edtShippingPostalcode.text.toString())) {
                    binding.edtShippingPostalcode.error = "Not Postalcode"
                }
            }
        }
        binding.btShippingContinue.setOnClickListener {
            if (
                viewModel.isPostalcode(binding.edtShippingPostalcode.text.toString()) &&
                viewModel.isvalidate(binding.edtShippingCountry.text.toString()) &&
                viewModel.isvalidate(binding.edtShippingCity.text.toString()) &&
                viewModel.isvalidate(binding.edtShippingAddress.text.toString())
            ){
                viewModel.navigate(ShippingCartFragmentDirections.actionShippingCartFragmentToPaymentFragment(
                    Orders(itemsPrice = navArgs.orders?.itemsPrice, orderItems = navArgs.orders?.orderItems, shippingAddress = ShippingAddress(
                        binding.edtShippingCountry.text.toString(),
                        binding.edtShippingAddress.text.toString(),
                        binding.edtShippingCity.text.toString(),
                        binding.edtShippingPostalcode.text.toString().toInt()
                    ))
                ))
            }
        }
    }

}