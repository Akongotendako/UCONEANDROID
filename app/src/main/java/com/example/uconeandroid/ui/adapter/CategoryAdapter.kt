package com.example.uconeandroid.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.uconeandroid.R

class CategoryAdapter(
    private val categories: List<String>,
    private val onSelectedCategory: (Int) -> Unit
): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = 0

    inner class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.categoryText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_items, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.textView.text = categories[position]

        val isSelectedCategory = selectedPosition == position
        holder.textView.background = ContextCompat.getDrawable(
            holder.itemView.context,
            if (isSelectedCategory) R.drawable.selected_sizes_bg else R.drawable.unselected_sizes_bg
        )

        holder.textView.setOnClickListener {
            val prev = selectedPosition
            selectedPosition = position
            notifyItemChanged(prev)
            notifyItemChanged(position)
            onSelectedCategory(position)
        }

    }

    override fun getItemCount(): Int = categories.size
}