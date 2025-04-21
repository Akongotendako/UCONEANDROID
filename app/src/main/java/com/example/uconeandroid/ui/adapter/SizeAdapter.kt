package com.example.uconeandroid.ui.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.uconeandroid.R

class SizeAdapter(
    private val sizes: List<String>,
    private val onSizeSelected: (Int) -> Unit
) : RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {

    private var selectedSizePosition = mutableSetOf<Int>()

    inner class SizeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.sizeText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.size_items, parent, false)
        return SizeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.textView.text = sizes[position]

        val isSelected = selectedSizePosition.contains(position)
        holder.textView.background = ContextCompat.getDrawable(
            holder.itemView.context,
            if (isSelected) R.drawable.selected_sizes_bg else R.drawable.unselected_sizes_bg
        )

        holder.textView.setOnClickListener {
            if(selectedSizePosition.contains(position)) {
                selectedSizePosition.remove(position)
            } else {
                selectedSizePosition.add(position)
            }
        }

        holder.itemView.post{
            notifyItemChanged(position)
            onSizeSelected(position)
        }
    }

    fun clearSelections() {
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = sizes.size
}