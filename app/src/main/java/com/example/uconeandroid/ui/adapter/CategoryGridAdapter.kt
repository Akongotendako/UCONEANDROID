package com.example.uconeandroid.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uconeandroid.R
import com.example.uconeandroid.data.model.ProductModel

class CategoryGridAdapter(
    private val productList: List<ProductModel>
): RecyclerView.Adapter<CategoryGridAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryGridAdapter.CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_menu, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
    }


    override fun getItemCount(): Int = productList.size
}