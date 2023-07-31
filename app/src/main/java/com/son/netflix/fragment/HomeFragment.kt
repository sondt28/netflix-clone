package com.son.netflix.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.son.netflix.R
import com.son.netflix.activity.PlayActivity
import com.son.netflix.adapter.ContinueFilmAdapter
import com.son.netflix.adapter.FilmItemAdapter
import com.son.netflix.databinding.FragmentHomeBinding
import com.son.netflix.model.Film

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        handlePreviewsFilm()
        handleContinueFilm()
        setupListener()

        return binding.root
    }

    private fun setupListener() {
        binding.tvMyLists.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_myListFragment)
        }
    }

    private fun handleContinueFilm() {
        val adapter = ContinueFilmAdapter()

        adapter.setupData(
            arrayListOf(
                Film(R.drawable.img_continue_film, "すずめ", R.raw.video),
                Film(R.drawable.img_continue_film, "すずめ", R.raw.video),
                Film(R.drawable.img_continue_film, "すずめ", R.raw.video),
                Film(R.drawable.img_continue_film, "すずめ", R.raw.video),
                Film(R.drawable.img_continue_film, "すずめ", R.raw.video),
                Film(R.drawable.img_continue_film, "すずめ", R.raw.video),
                Film(R.drawable.img_continue_film, "すずめ", R.raw.video),
                Film(R.drawable.img_continue_film, "すずめ", R.raw.video),
            )
        )

        binding.rcContinueFilm.adapter = adapter

        adapter.onClickItem = {
            val intent = Intent(requireContext(), PlayActivity::class.java)

            intent.putExtra("key", it)
            startActivity(intent)
        }
    }

    private fun handlePreviewsFilm() {
        val adapter = FilmItemAdapter()

        adapter.setupData(
            arrayListOf(
                Film(R.drawable.img_film_one, "", R.raw.video),
                Film(R.drawable.img_flim_two, "", R.raw.video),
                Film(R.drawable.img_flim_three, "", R.raw.video),
                Film(R.drawable.img_film_four, "", R.raw.video),
                Film(R.drawable.img_flim_two, "", R.raw.video),
                Film(R.drawable.img_flim_two, "", R.raw.video),
                Film(R.drawable.img_flim_two, "", R.raw.video),
                Film(R.drawable.img_flim_two, "", R.raw.video),
            )
        )

        binding.rcPreviewFilm.adapter = adapter

        adapter.onClickItem = {
            val intent = Intent(requireContext(), PlayActivity::class.java)

            intent.putExtra("key", it)
            startActivity(intent)
        }
    }
}