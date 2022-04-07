package com.ajgroup.priceacsimulation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajgroup.priceacsimulation.database.AcEntity
import com.ajgroup.priceacsimulation.databinding.ListItemBinding

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
            tvMerk.text = "Merk:"+acList[position].merk
            tvUkuran.text = "Ukuran: "+acList[position].ukuran
            tvBuatan.text = "Buatan: " + acList[position].buatan
            tvHarga.text  = "Harga: Rp. " + acList[position].harga
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