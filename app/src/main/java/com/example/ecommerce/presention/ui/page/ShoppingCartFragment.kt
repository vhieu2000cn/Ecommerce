package com.example.ecommerce.presention.ui.page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentShoppingCartBinding
import com.example.ecommerce.domain.model.OrderItemsItem
import com.example.ecommerce.domain.model.Orders
import com.example.ecommerce.presention.base.BaseFragment
import com.example.ecommerce.presention.ui.adapter.ListShoppingCartAdapter
import com.example.ecommerce.presention.ui.viewmodel.ShoppingCartViewModel
import com.example.ecommerce.presention.ui.viewmodel.SplashViewModel
import kotlin.math.log

class ShoppingCartFragment : BaseFragment<FragmentShoppingCartBinding, ShoppingCartViewModel>() {
    override val layoutId: Int = R.layout.fragment_shopping_cart
    override val viewModel: ShoppingCartViewModel by activityViewModels()
    private val listProductOrder = mutableListOf<OrderItemsItem>()

    private val splashViewModel: SplashViewModel by activityViewModels()

    override fun onReady(savedInstanceState: Bundle?) {
        viewModel.getShoppingCart()
        splashViewModel.getLoggedFromDS()
        initDataRcv()
        initData()
    }

    private fun initData() {
        viewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.tvTotal.text = "Total: $${it}"
            if (it == 0.0) {
                binding.btSpcCheckout.visibility = View.GONE
            }else{
                binding.btSpcCheckout.visibility = View.VISIBLE
            }
        }
        binding.btSpcCheckout.setOnClickListener {
            if (splashViewModel.isLogged.value != null && splashViewModel.logged.value != null) {
                viewModel.navigate(
                    ShoppingCartFragmentDirections.actionShoppingCartFragmentToShippingCartFragment(
                        Orders(
                            itemsPrice = viewModel.totalPrice.value.toString(),
                            orderItems = listProductOrder
                        )
                    )
                )
            } else {
                viewModel.navigate(ShoppingCartFragmentDirections.actionShoppingCartFragmentToLoginFragment())
            }
        }
    }

    private fun initDataRcv() {
        val adapter = ListShoppingCartAdapter(viewModel)
        binding.itemShoppingCart.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setAdapter(adapter)
        }
        viewModel.shoppingCartItem.observe(viewLifecycleOwner){
            it?.let { listSC ->
                adapter.setData(listSC)
                listSC.forEach { spc ->
                    listProductOrder.add(
                        OrderItemsItem(
                            spc.product.image,
                            spc.product.id,
                            spc.product.countInStock,
                            spc.product.price,
                            spc.qualityProduct,
                            spc.product.name
                        )
                    )
                }
            }
        }
    }
}