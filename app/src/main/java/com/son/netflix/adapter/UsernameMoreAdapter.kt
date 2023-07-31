package com.son.netflix.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.son.netflix.model.User
import com.son.netflix.databinding.CategoryItemBinding

class UsernameMoreAdapter : RecyclerView.Adapter<UsernameMoreAdapter.CategoryViewHolder>() {
    var onClickItem: ((User) -> Unit)? = null
    private var listCategories = ArrayList<User>()

    fun setupData(newData: List<User>) {
        listCategories.addAll(newData)
    }

    inner class CategoryViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            binding.ivCategoryImage.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.ivCategoryImage.context,
                    item.image
                )
            )
            binding.tvCategoryName.text = item.name

            binding.root.setOnClickListener {
                onClickItem?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = listCategories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(listCategories[position])
    }
}