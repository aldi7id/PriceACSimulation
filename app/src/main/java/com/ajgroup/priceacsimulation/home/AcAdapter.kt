package com.ajgroup.priceacsimulation.home

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ajgroup.priceacsimulation.MainActivity
import com.ajgroup.priceacsimulation.database.AcEntity
import com.ajgroup.priceacsimulation.database.RegisterDatabase
import com.ajgroup.priceacsimulation.database.RegisterEntity
import com.ajgroup.priceacsimulation.databinding.CustomDialogBinding
import com.ajgroup.priceacsimulation.databinding.ListItemBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AcAdapter(private val acList :List<AcEntity>,
                val editClick:(AcEntity)-> Unit,
                val deleteClick:(AcEntity)-> Unit,
                ):RecyclerView.Adapter<AcAdapter.ViewHolder>() {
    class ViewHolder(val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            tvTitle.text = acList[position].title
            tvMerk.text = acList[position].merk
            tvHarga.text  = acList[position].harga
            ibEdit.setOnClickListener {
                editClick(acList[position])
            }
            ibDelete.setOnClickListener {
                deleteClick(acList[position])
            }
        }
    }

    override fun getItemCount(): Int = acList.size
}