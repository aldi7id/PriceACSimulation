package com.ajgroup.priceacsimulation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajgroup.priceacsimulation.database.AcEntity
import com.ajgroup.priceacsimulation.database.RegisterDatabase
import com.ajgroup.priceacsimulation.databinding.ListItemBinding

class AcAdapter:RecyclerView.Adapter<AcAdapter.ViewHolder>() {
    private val listAc = mutableListOf<AcEntity>()
    class ViewHolder(val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            fab.setOnClickListener {
                var mDb: RegisterDatabase? = null
                mDb = RegisterDatabase.getInstance(this)
                val builder = Alr
            }
        }
    }

    override fun getItemCount(): Int = listAc.size
}