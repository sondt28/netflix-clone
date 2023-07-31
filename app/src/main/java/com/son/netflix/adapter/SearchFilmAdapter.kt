package com.son.netflix.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.son.netflix.model.Film
import com.son.netflix.databinding.SearchFilmItemBinding

class SearchFilmAdapter : RecyclerView.Adapter<SearchFilmAdapter.SearchFilmViewHolder>(),
    Filterable {
    var onClickItem: ((Film) -> Unit)? = null

    private val listDataFilm = ArrayList<Film>()
    private var listDataFilmFiltered = ArrayList<Film>()

    fun setupData(newData: List<Film>) {
        listDataFilm.clear().also {
            listDataFilm.addAll(newData)
            filter.filter("")
        }
    }

    inner class SearchFilmViewHolder(val binding: SearchFilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Film) {
            binding.tvFilmTitle.text = item.name
            binding.ivFilmImage.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.ivFilmImage.context,
                    item.image
                )
            )

            binding.root.setOnClickListener {
                onClickItem?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFilmViewHolder {
        return SearchFilmViewHolder(
            SearchFilmItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = listDataFilmFiltered.size

    override fun onBindViewHolder(holder: SearchFilmViewHolder, position: Int) {
        holder.bind(listDataFilmFiltered[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                listDataFilmFiltered = if (charString.isEmpty()) {
                    listDataFilm
                } else {
                    val filteredList = ArrayList<Film>()
                    for (film in listDataFilm) {
                        if ((film.name).lowercase().contains(charString.lowercase())) {
                            filteredList.add(film)
                        }
                    }
                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = listDataFilmFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listDataFilmFiltered = results?.values as ArrayList<Film>
                notifyDataSetChanged()
            }
        }
    }
}
