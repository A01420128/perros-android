package com.example.perros.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.perros.R

class PerrosAdapter (private val images : List<String>) : RecyclerView.Adapter<PerroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.perro_foto, parent, false)
        return PerroViewHolder(view)
    }

    override fun onBindViewHolder(holder: PerroViewHolder, position: Int) {
        val perroUrl = images[position]
        holder.bind(perroUrl)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}