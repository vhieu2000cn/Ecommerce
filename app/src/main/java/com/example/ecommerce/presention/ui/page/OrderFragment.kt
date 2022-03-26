package com.example.ecommerce.presention.ui.page

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentOrderBinding
import com.example.ecommerce.domain.model.OrderItemsItem
import com.example.ecommerce.presention.base.BaseFragment
import com.example.ecommerce.presention.ui.adapter.ListOrderAdapter
import com.example.ecommerce.presention.ui.viewmodel.OrderViewModel
import com.example.ecommerce.util.Status
import kotlin.math.round


class OrderFragment : BaseFragment<FragmentOrderBinding, OrderViewModel>() {
    private var mPriceShip = 0
    private var mTax = 0
    private var mItems = 0
    private var mItemsItem = 0.0
    private var listOrder: MutableList<OrderItemsItem> = mutableListOf()
    override val layoutId: Int = R.layout.fragment_order
    override val viewModel: OrderViewModel by activityViewModels()
    private val navArgs: OrderFragmentArgs by navArgs()

    override fun onReady(savedInstanceState: Bundle?) {
        initData()
        initDataRcv()
    }

    private fun initDataRcv() {
        val adapter = ListOrderAdapter()
        navArgs.orders?.orderItems?.forEach {
            if (it != null) {
                listOrder.clear()
                listOrder.add(it)
            }
        }
        adapter.setData(listOrder)
        binding.rcvOrderItem.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setAdapter(adapter)
        }
    }

    private fun initData() {
        binding.tvOrderAdress.text =
            "Address: ${navArgs.orders?.shippingAddress?.address},${navArgs?.orders?.shippingAddress?.city},${navArgs?.orders?.shippingAddress?.postalCode},${navArgs?.orders?.shippingAddress?.country}"
        binding.tvOrderPayment.text = "Method: ${navArgs.orders?.paymentMethod}"
        binding.tvOrderItems.text = "$${navArgs.orders?.itemsPrice}"
        if (navArgs.orders?.itemsPrice?.toDouble() != null) {
            mItemsItem = round(navArgs.orders!!.itemsPrice!!.toDouble() * 10000) / 10000
        }
        if (mItemsItem < 500.0) {
            mPriceShip = navArgs.orders?.orderItems?.size ?: 0 * 20
            binding.tvOrderShipping.text = "$${mPriceShip}"
        } else {
            binding.tvOrderShipping.text = "$0"
        }
        mTax = (mItemsItem / 10).toInt()
        binding.tvOrderTax.text = "$${mTax}"
        binding.tvOrderTotal.text = "$${mPriceShip + mTax + mItemsItem}"

        binding.btOrderContinue.setOnClickListener {
            if (navArgs.orders != null && viewModel.token.value != null){
                Log.d("OrderFragment", "ok: "+viewModel.token.value!!)
                viewModel.orderToApi(navArgs.orders!!,"Bearer ${viewModel.token.value!!}").observe(viewLifecycleOwner) {
                    when (it.status) {
                        Status.LOADING -> {
                            binding.pbOrder.visibility = View.VISIBLE
                        }
                        Status.SUCCESS -> {
                            if (it.data != null){
                                binding.pbOrder.visibility = View.GONE
                                binding.btOrderContinue.visibility = View.GONE
                                binding.tvOrderPass.visibility = View.VISIBLE
                                binding.tvOrderPass.text = "Create at: ${it.data.createdAt}"
                                binding.btOrderHome.visibility = View.VISIBLE
                            }
                        }
                        Status.ERROR -> {
                            binding.pbOrder.visibility = View.GONE
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        binding.btOrderHome.setOnClickListener {
            viewModel.navigate(OrderFragmentDirections.actionOrderFragmentToHomeFragment())
        }
    }

}