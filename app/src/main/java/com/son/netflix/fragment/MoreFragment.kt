package com.son.netflix.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.son.netflix.R
import com.son.netflix.adapter.UsernameMoreAdapter
import com.son.netflix.databinding.FragmentMoreBinding
import com.son.netflix.model.User

class MoreFragment : Fragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!
    private var userMoreAdapter: UsernameMoreAdapter? = null
    private var users: List<User>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        val view = binding.root

        handleDataCategory()

        return view
    }

    private fun handleDataCategory() {
        users = listOf(
            User(R.drawable.img_category_one, "Emenalo"),
            User(R.drawable.img_category_two, "Onyeka"),
            User(R.drawable.img_category_three, "Thelma"),
            User(R.drawable.img_category_four, "Kids"),
            User(R.drawable.img_category_plus, "")
        )

        binding.rcCategory.apply {
            var layoutParam =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            layoutManager = layoutParam
            userMoreAdapter = UsernameMoreAdapter()
            userMoreAdapter?.setupData(users ?: listOf())
            adapter = userMoreAdapter

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}