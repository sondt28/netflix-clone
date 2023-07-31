package com.son.netflix.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.son.netflix.model.Film
import com.son.netflix.databinding.FilmItemBinding

class FilmItemAdapter : RecyclerView.Adapter<FilmItemAdapter.FilmItemViewHolder>() {

    var onClickItem: ((Film) -> Unit)? = null
    private var listDataFilm = ArrayList<Film>()

    fun setupData(newData: ArrayList<Film>) {
        listDataFilm = newData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemViewHolder {
        return FilmItemViewHolder(
            FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = listDataFilm.size

    override fun onBindViewHolder(holder: FilmItemViewHolder, position: Int) {
        val item = listDataFilm[position]
        holder.bind(item)
    }

    inner class FilmItemViewHolder(val binding: FilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Film) {
            binding.ivFilm.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.ivFilm.context,
                    item.image
                )
            )
            binding.root.setOnClickListener {
                onClickItem?.invoke(item)
            }
        }
    }
}