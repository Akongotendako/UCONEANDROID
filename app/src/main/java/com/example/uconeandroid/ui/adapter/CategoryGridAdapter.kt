package com.example.uconeandroid.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uconeandroid.R
import com.example.uconeandroid.data.model.ProductModel

class CategoryGridAdapter(
    private var productList: List<ProductModel>,
    private val onClick: (ProductModel) -> Unit
): RecyclerView.Adapter<CategoryGridAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val card: LinearLayout = view.findViewById(R.id.shopCategoryListCard)
        val productImage: ImageView = view.findViewById(R.id.shopCategoryListImage)
        val productTitle: TextView = view.findViewById(R.id.shopCategoryListTitle)
        val productPrice: TextView = view.findViewById(R.id.shopCategoryListPrice)

        init {
            view.setOnClickListener {
               val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClick(productList[position])
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<ProductModel>) {
        productList = newList
        notifyDataSetChanged()
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

        val product = productList[position]

        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.productImage)

        holder.productTitle.text = product.productName
        holder.productPrice.text = "₱${product.price}"

        holder.card.setOnClickListener {

        }
    }



    override fun getItemCount(): Int = productList.size
}