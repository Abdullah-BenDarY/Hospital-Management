package com.example.hospitalmanagement.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalmanagement.R


class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.Holder>() {

    var list: List<String>? = null
    var selectedItem = 0

    private var onCategoryClickListener: ((String) -> Unit)? = null

    fun setOnCategoryClickListener(listener: (String) -> Unit) {
        onCategoryClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tab_type, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val type = list?.get(position)

        holder.apply {
            textType.text = type
            if (selectedItem == position) {
                textType.setBackgroundResource(R.drawable.ic_background_selected)
                textType.setTextColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.white
                    )
                )
            } else {
                textType.setBackgroundResource(R.drawable.ic_item_tab_background)
                textType.setTextColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.black
                    )
                )
            }
        }
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val textType: TextView = view.findViewById(R.id.text_type)

        init {
            itemView.setOnClickListener {
                val previousSelected = selectedItem
                selectedItem = adapterPosition
                notifyItemChanged(previousSelected)
                notifyItemChanged(selectedItem)

                // Pass the selected category back to the fragment
                list?.get(selectedItem)?.let { category ->
                    onCategoryClickListener?.invoke(category)
                }
            }
        }
    }
}
