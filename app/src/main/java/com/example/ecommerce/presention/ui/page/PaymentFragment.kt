package com.example.ecommerce.presention.ui.page

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentPaymentBinding
import com.example.ecommerce.domain.model.Orders
import com.example.ecommerce.presention.base.BaseFragment
import com.example.ecommerce.presention.ui.viewmodel.PaymentViewModel


class PaymentFragment : BaseFragment<FragmentPaymentBinding, PaymentViewModel>() {
    private var paymentMethod: String? = null
    override val layoutId: Int = R.layout.fragment_payment
    override val viewModel: PaymentViewModel by activityViewModels()
    private val navArgs: PaymentFragmentArgs by navArgs()


    override fun onReady(savedInstanceState: Bundle?) {
        initSpiner()
    }

    private fun initSpiner() {
        binding.btPaymentPp.setOnClickListener{
            viewModel.navigate(
                PaymentFragmentDirections.actionPaymentFragmentToOrderFragment(
                    Orders(
                        itemsPrice = navArgs.orders?.itemsPrice,
                        orderItems = navArgs.orders?.orderItems,
                        shippingAddress = navArgs.orders?.shippingAddress,
                        paymentMethod = "Paypal"
                    )
                )
            )
        }
        binding.btPaymentCc.setOnClickListener{
            viewModel.navigate(
                PaymentFragmentDirections.actionPaymentFragmentToOrderFragment(
                    Orders(
                        itemsPrice = navArgs.orders?.itemsPrice,
                        orderItems = navArgs.orders?.orderItems,
                        shippingAddress = navArgs.orders?.shippingAddress,
                        paymentMethod = "Credit Card"
                    )
                )
            )
        }
    }

}