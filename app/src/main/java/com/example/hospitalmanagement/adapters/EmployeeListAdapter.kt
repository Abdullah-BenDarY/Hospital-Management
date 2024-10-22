package com.example.hospitalmanagement.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.UsersData
import com.example.hospitalmanagement.R


class EmployeeListAdapter : RecyclerView.Adapter<EmployeeListAdapter.Holder>() {

     var list : List<UsersData> ?= null

    private var onItemClickListener: ((UsersData) -> Unit)? =null
    fun setOnItemClickListener(listener: (UsersData)->Unit){
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_employee,parent,false)
        )
    }
    override fun getItemCount(): Int {
        return list?.size!!
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = list?.get(position)
        holder.bind(user)
    }


    inner class Holder (  view: View): RecyclerView.ViewHolder(view) {

        val textName: TextView = view.findViewById(R.id.text_username)
        val textDetails: TextView = view.findViewById(R.id.text_details)
        val imageUser: ImageView = view.findViewById(R.id.img_user)

        fun bind(user : UsersData?){
            textName.text = user?.first_name
            textDetails.text = user?.type
          //  Glide.with(itemView).load(user?.avatar).into(imageUser)
            itemView.setOnClickListener {
                onItemClickListener?.let {
                    if (user != null) {
                        it(user)
                    }
                }
            }
        }
    }
}



