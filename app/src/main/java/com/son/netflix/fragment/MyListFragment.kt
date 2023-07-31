package com.son.netflix.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.son.netflix.R
import com.son.netflix.activity.PlayActivity
import com.son.netflix.adapter.MyListAdapter
import com.son.netflix.databinding.FragmentMyListBinding
import com.son.netflix.model.Film

class MyListFragment : Fragment() {
    private var _binding: FragmentMyListBinding? = null
    private val binding get() = _binding!!
    private var films: List<Film>? = null
    private var filmAdapter: MyListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyListBinding.inflate(inflater, container, false)
        val view = binding.root

        handleFilm()

        return view
    }

    private fun handleFilm() {
        films = listOf(
            Film(R.drawable.img_my_list_one, "", R.raw.video),
            Film(R.drawable.img_my_list_two, "", R.raw.video),
            Film(R.drawable.img_my_list_three, "", R.raw.video),
            Film(R.drawable.img_my_list_four, "", R.raw.video),
            Film(R.drawable.img_my_list_five, "", R.raw.video),
            Film(R.drawable.img_my_list_six, "", R.raw.video),
            Film(R.drawable.img_my_list_seven, "", R.raw.video),
            Film(R.drawable.img_my_list_eight, "", R.raw.video),
            Film(R.drawable.img_my_list_nine, "", R.raw.video),
            Film(R.drawable.img_my_list_ten, "", R.raw.video),
            Film(R.drawable.img_my_list_eleven, "", R.raw.video),
            Film(R.drawable.img_my_list_twelve, "", R.raw.video),
            Film(R.drawable.img_my_list_thirtteen, "", R.raw.video),
            Film(R.drawable.img_my_list_four, "", R.raw.video),
            Film(R.drawable.img_my_list_fifteen, "", R.raw.video),
        )
        binding.rcMyList.apply {
            filmAdapter = MyListAdapter()
            filmAdapter?.setupData(films ?: listOf())
            adapter = filmAdapter

            filmAdapter?.onClickItem = {
                val intent = Intent(requireContext(), PlayActivity::class.java)

                intent.putExtra("key", it)
                startActivity(intent)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}