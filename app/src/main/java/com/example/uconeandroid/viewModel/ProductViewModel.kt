package com.example.uconeandroid.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uconeandroid.data.model.ProductModel
import com.example.uconeandroid.data.repository.ProductRepository
import com.example.uconeandroid.utils.ResultState
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class ProductViewModel : ViewModel() {

    private val productRepository = ProductRepository()

    private val _products = MutableLiveData<ResultState<List<ProductModel>>>()
    val products: LiveData<ResultState<List<ProductModel>>> get() = _products

    private val _product = MutableLiveData<ResultState<ProductModel>>()
    val product: LiveData<ResultState<ProductModel>> get() = _product

    private val _uploadProductImage = MutableLiveData<ResultState<String>>()
    val uploadProductImage: LiveData<ResultState<String>> = _uploadProductImage

    init {
        fetchProducts()
    }
    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = productRepository.fetchProducts()
                val body = response.body()
                if (response.isSuccessful && body != null && body.success) {
                    _products.value = ResultState.Success(body.data!!)
                } else {
                    _products.value = ResultState.Error(response.errorBody()?.string()!!)
                }
            } catch (e: Exception) {
                _products.value = ResultState.Error(e.message!!)
            }
        }
    }

    fun uploadProductImage(image: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                val response = productRepository.uploadProductImage(image)
                val body = response.body()
                if (response.isSuccessful && body != null && body.success) {
                    _uploadProductImage.value = ResultState.Success(body.data!!)
                } else {
                    Log.e("UploadImage", "Upload failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("UploadImage", "Exception during upload: ${e.message}")
            }

        }
    }


    fun addProduct(
        productModel: ProductModel
    ) {
        _product.value = ResultState.Loading
        viewModelScope.launch {
            try {
                val response = productRepository.addProduct(productModel)
                val body = response.body()
                if (response.isSuccessful && body != null && body.success) {
                    _product.value = ResultState.Success(body.data!!)
                } else {
                    _product.value = ResultState.Error(response.errorBody()?.string()!!)
                }
            } catch (e: Exception) {
                _product.value = ResultState.Error(e.message!!)
            }
        }
    }

    fun reset() {
        _uploadProductImage.value = ResultState.Loading
        _product.value = ResultState.Loading

    }
}