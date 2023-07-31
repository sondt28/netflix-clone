package com.son.netflix.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.son.netflix.databinding.MyListFilmItemBinding
import com.son.netflix.model.Film

class MyListAdapter : RecyclerView.Adapter<MyListAdapter.MyListViewHolder>() {
    var onClickItem: ((Film) -> Unit)? = null

    private var myListData = ArrayList<Film>()

    fun setupData(data: List<Film>) {
        myListData.addAll(data)
    }

    inner class MyListViewHolder(val binding: MyListFilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Film) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        return MyListViewHolder(
            MyListFilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = myListData.size

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        holder.bind(myListData[position])
    }
}