package com.example.uconeandroid.ui.fragment.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uconeandroid.data.model.ProductModel
import com.example.uconeandroid.databinding.FragmentAdminShopUpdateItemBinding

class AdminShopUpdateItemFragment: Fragment() {

    private var _binding: FragmentAdminShopUpdateItemBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val PRODUCT = "product"

        fun newInstance(productModel: ProductModel): AdminShopUpdateItemFragment {
            val fragment = AdminShopUpdateItemFragment()
            fragment.arguments = Bundle().apply {
                putParcelable(PRODUCT, productModel)
            }
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentAdminShopUpdateItemBinding.inflate(inflater, container, false)
        return binding.root
    }
}