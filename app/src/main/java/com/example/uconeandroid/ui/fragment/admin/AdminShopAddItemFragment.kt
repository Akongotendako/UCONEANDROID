package com.example.uconeandroid.ui.fragment.admin

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uconeandroid.R
import com.example.uconeandroid.data.model.ProductModel
import com.example.uconeandroid.databinding.FragmentAdminShopAddItemBinding
import com.example.uconeandroid.ui.adapter.CategoryAdapter
import com.example.uconeandroid.ui.adapter.SizeAdapter
import com.example.uconeandroid.utils.ResultState
import com.example.uconeandroid.utils.showDialog
import com.example.uconeandroid.viewModel.ProductViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AdminShopAddItemFragment : Fragment() {

    private var _binding: FragmentAdminShopAddItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductViewModel by viewModels()
    private var selectedImageUri: Uri? = null
    private lateinit var sizeAdapter: SizeAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private var selectedSizes: MutableList<String> = mutableListOf()
    private var selectedCategory: String = "Lanyard"

    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
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
        initializeCategory()
        addProduct()
        navigateBackToShop()
    }

    private fun initializeSizes() {
        val sizes = listOf("Small", "Medium", "Large", "Extra Large")
        sizeAdapter = SizeAdapter(sizes) {
            val isSelectedSize = selectedSizes.contains(sizes[it])
            if (isSelectedSize) selectedSizes.remove(sizes[it]) else selectedSizes.add(sizes[it])

        }

        binding.recycleSizes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recycleSizes.adapter = sizeAdapter
    }

    private fun initializeCategory() {
        val categories = listOf("Lanyard", "T-Shirt", "Uniform")
        categoryAdapter = CategoryAdapter(categories) {
            Log.e("Category", categories[it])
            selectedCategory = categories[it]
        }
        binding.recycleCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recycleCategory.adapter = categoryAdapter
    }

    private fun pickAnImage() {
        binding.pickImageButton.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }
    }


    private fun addProduct() {
        binding.addProduct.setOnClickListener {

            val productName = binding.productName.text.toString()
            val price = binding.price.text.toString()
            val stock = binding.stock.text.toString()
            val discount = binding.discount.text.toString()
            val description = binding.description.text.toString()
            val progressBar = binding.progressBar

            Log.e("Sizes", selectedSizes.size.toString())

            val product = ProductModel(
                productName = productName,
                description = description,
                price = price,
                sizes = selectedSizes,
                stock = stock,
                category = selectedCategory,
                discount = discount
            )

            viewModel.uploadProductImage.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ResultState.Error -> {
                        progressBar.visibility = View.GONE
                        requireContext().showDialog(success = false)
                    }

                    ResultState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }

                    is ResultState.Success -> {
                        progressBar.visibility = View.GONE
                        val imageUrl = result.data
                        val finalProduct = product.copy(
                            imageUrl = imageUrl
                        )

                        viewModel.addProduct(finalProduct)
                    }
                }
            }

            viewModel.product.observe(viewLifecycleOwner) {
                when (it) {
                    is ResultState.Error -> {
                        requireContext().showDialog(success = false)
                        progressBar.visibility = View.GONE
                    }

                    ResultState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }

                    is ResultState.Success -> {
                        progressBar.visibility = View.GONE
                        requireContext().showDialog(success = true)
                        viewModel.reset()
                        reset()
                    }
                }
            }

            binding.progressBar.visibility = View.VISIBLE

            if (selectedImageUri != null) {
                uploadImageFromUri(selectedImageUri!!)
            }
        }

    }


    private fun reset() {

        binding.productName.setText("")
        binding.price.setText("")
        binding.stock.setText("")
        binding.discount.setText("")
        binding.description.setText("")
        binding.progressBar.visibility = View.GONE
        binding.imageView.setImageDrawable(null)
        binding.pickImageButton.visibility = View.VISIBLE

        selectedCategory = "Lanyard"
        selectedSizes.clear()
        sizeAdapter.clearSelections()

        selectedImageUri = null
    }

    private fun uploadImageFromUri(uri: Uri) {
        val context = requireContext()
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(uri) ?: return
        val tempFile = File.createTempFile("temp_", ".jpg", context.cacheDir)
        tempFile.outputStream().use {
            inputStream.copyTo(it)
        }

        val requestBody = tempFile
            .asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("image", tempFile.name, requestBody)
        viewModel.uploadProductImage(body)
    }

    private fun navigateBackToShop() {
        binding.addItemBackIconButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AdminShopFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}