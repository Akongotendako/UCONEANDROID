package com.example.uconeandroid.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uconeandroid.data.model.ProductModel
import com.example.uconeandroid.data.repository.ProductRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class ProductViewModel: ViewModel() {

    private val productRepository = ProductRepository()

    private val _products = MutableLiveData<List<ProductModel>>()
    val products: LiveData<List<ProductModel>> get() = _products

    private val _product = MutableLiveData<Result<ProductModel>>()
    val product: LiveData<Result<ProductModel>> get() = _product


    fun addProduct(
        productName: String,
        description: String,
        price: String,
        stock: String,
        discount: String,
        image: MultipartBody.Part,
        sizes: List<String>,
        category: String
    ) {
        viewModelScope.launch {
            try {
                val response = productRepository.addProduct(
                    productName,
                    description,
                    price,
                    stock,
                    discount,
                    image,
                    sizes,
                    category
                )
                if (response.isSuccessful) {
                    _product.postValue(Result.success(response.body()!!))
                } else {
                    _product.postValue(Result.failure(Exception("Product failed to add")))

                }
            } catch (e: Exception) {
                _product.postValue((Result.failure(e)))
            }
        }
    }
}