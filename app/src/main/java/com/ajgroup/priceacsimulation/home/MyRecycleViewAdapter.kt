package com.ajgroup.priceacsimulation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajgroup.priceacsimulation.R
import com.ajgroup.priceacsimulation.database.RegisterEntity
import com.ajgroup.priceacsimulation.databinding.ListItemBinding

class MyRecycleViewAdapter(private val usersList :List<RegisterEntity>): RecyclerView.Adapter<MyRecycleViewAdapter.MyviewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        //val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyviewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    class MyviewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: RegisterEntity) {
            with(binding){
            FirstNameTextView.text = user.firstName
            secondNameTextView.text = user.lastName
            userTextField.text = user.userName
            }
        }
    }
}