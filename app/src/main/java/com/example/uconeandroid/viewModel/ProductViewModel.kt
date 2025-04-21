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

    private val _products = MutableLiveData<List<ProductModel>>()
    val products: LiveData<List<ProductModel>> get() = _products

    private val _product = MutableLiveData<ResultState<ProductModel>>()
    val product: LiveData<ResultState<ProductModel>> get() = _product

    private val _uploadProductImage = MutableLiveData<String>()
    val uploadProductImage: LiveData<String> = _uploadProductImage

    fun uploadProductImage(image: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                val response = productRepository.uploadProductImage(image)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _uploadProductImage.value = it
                    } ?: Log.e("UploadImage", "Response success but body is null")
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
                if (response.isSuccessful) {
                    _product.value = ResultState.Success(response.body()!!)
                } else {
                    _product.value = ResultState.Error(response.errorBody()?.string()!!)
                }
            } catch (e: Exception) {
                _product.value = ResultState.Error(e.message!!)
            }
        }
    }
}