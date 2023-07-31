package com.son.netflix.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.son.netflix.databinding.FilmComingSoonItemBinding
import com.son.netflix.model.Film

class ComingSoonFilmAdapter :
    RecyclerView.Adapter<ComingSoonFilmAdapter.ComingSoonFilmViewHolder>() {

    private var listData = ArrayList<Film>()

    fun setupData(data: List<Film>) {
        listData.addAll(data)
    }

    inner class ComingSoonFilmViewHolder(val binding: FilmComingSoonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Film) {
            binding.tvFilmName.text = item.name
            binding.ivFilmImage.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.ivFilmImage.context,
                    item.image
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingSoonFilmViewHolder {
        return ComingSoonFilmViewHolder(
            FilmComingSoonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ComingSoonFilmViewHolder, position: Int) {
        holder.bind(listData[position])
    }
}