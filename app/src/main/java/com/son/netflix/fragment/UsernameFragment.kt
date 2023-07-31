package com.son.netflix.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.son.netflix.R
import com.son.netflix.adapter.UsernameMoreAdapter
import com.son.netflix.databinding.FragmentUsernameBinding
import com.son.netflix.model.User

class UsernameFragment : Fragment() {
    private var _binding: FragmentUsernameBinding? = null
    private val binding get() = _binding!!
    private var userAdapter: UsernameMoreAdapter? = null
    private var users: List<User>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsernameBinding.inflate(inflater, container, false)
        val view = binding.root

        handlerUser()

        return view
    }

    private fun handlerUser() {
        users = listOf(
            User(R.drawable.img_category_one, "Emenalo"),
            User(R.drawable.img_category_two, "Onyeka"),
            User(R.drawable.img_category_three, "Thelma"),
            User(R.drawable.img_category_four, "Kids")
        )

        binding.rcUsername.apply {
            var listParam = GridLayoutManager(requireContext(), 2)
            layoutManager = listParam

            userAdapter = UsernameMoreAdapter()
            userAdapter?.setupData(users ?: listOf())
            adapter = userAdapter

            userAdapter?.onClickItem = {
                findNavController().navigate(R.id.action_usernameFragment_to_homeFragment)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<View>(R.id.bottomNavigationView)?.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        requireActivity().findViewById<View>(R.id.bottomNavigationView)?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}