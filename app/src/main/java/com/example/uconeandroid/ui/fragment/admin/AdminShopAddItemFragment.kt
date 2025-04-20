package com.example.uconeandroid.ui.fragment.admin

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uconeandroid.databinding.FragmentAdminShopAddItemBinding
import com.example.uconeandroid.ui.adapter.SizeAdapter
import com.example.uconeandroid.viewModel.ProductViewModel

class AdminShopAddItemFragment: Fragment() {

    private var _binding: FragmentAdminShopAddItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductViewModel by viewModels()
    private var selectedImageUri: Uri? = null
    private lateinit var adapter: SizeAdapter

    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            binding.imageView.setImageURI(it)
            binding.pickImageButton.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminShopAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pickAnImage()
        initializeSizes()
    }

    private fun initializeSizes() {
        val sizes = listOf("Small", "Medium", "Large", "Extra Large")
        adapter = SizeAdapter(sizes){
            val selectedSize = sizes[it]
        }

        binding.recycleSizes.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recycleSizes.adapter = adapter
    }

    private fun pickAnImage() {
        binding.pickImageButton.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }
    }


    private fun addProduct() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}