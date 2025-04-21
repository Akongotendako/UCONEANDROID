package com.example.uconeandroid.ui.fragment.admin


import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uconeandroid.R
import com.example.uconeandroid.databinding.FragmentAdminShopBinding
import com.example.uconeandroid.ui.adapter.CategoryAdapter
import com.example.uconeandroid.ui.adapter.CategoryGridAdapter
import com.example.uconeandroid.utils.ResultState
import com.example.uconeandroid.viewModel.ProductViewModel

class AdminShopFragment : Fragment() {

    private var _binding: FragmentAdminShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryGridAdapter: CategoryGridAdapter
    private var selectedCategory = "All"
    private val viewmodel: ProductViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminShopBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToAddItemScreen(view)
        initializeCategoryMenu()
        initializeCategoryGrid()
    }

    private fun initializeCategoryMenu() {
        val categories = listOf("All", "Lanyard", "Uniform", "T-Shirt")
        categoryAdapter = CategoryAdapter(categories) {
            selectedCategory = categories[it]
        }
        binding.shopCategoryMenu.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.shopCategoryMenu.adapter = categoryAdapter
    }

    private fun initializeCategoryGrid() {
        viewmodel.products.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultState.Error -> {}
                ResultState.Loading -> {}
                is ResultState.Success -> {
                    categoryGridAdapter = CategoryGridAdapter(result.data)
                    binding.shopCategoryList.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.shopCategoryList.adapter = categoryGridAdapter

                    val spacing = resources.getDimensionPixelSize(R.dimen.grid_spacing)
                    binding.shopCategoryList.addItemDecoration(object :
                        RecyclerView.ItemDecoration() {
                        override fun getItemOffsets(
                            outRect: Rect,
                            view: View,
                            parent: RecyclerView,
                            state: RecyclerView.State
                        ) {
                            outRect.set(spacing, spacing, spacing, spacing)
                        }
                    })
                }
            }
        }


    }

    private fun navigateToAddItemScreen(view: View) {
        view.findViewById<Button>(R.id.adminShopAddItem).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AdminShopAddItemFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}