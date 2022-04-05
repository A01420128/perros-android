package com.example.perros.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.perros.databinding.PerroFotoBinding
import com.squareup.picasso.Picasso

class PerroViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val binding = PerroFotoBinding.bind(view)

    fun bind(imageUrl: String) {
        // Picasso.get().load(imageUrl).into(binding.fotoPerro)
        Glide.with(binding.root)
            .load(imageUrl)
            .centerCrop()
            .into(binding.fotoPerro)
    }

}