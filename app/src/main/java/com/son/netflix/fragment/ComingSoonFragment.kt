package com.son.netflix.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.son.netflix.R
import com.son.netflix.adapter.ComingSoonFilmAdapter
import com.son.netflix.databinding.FragmentComingSoonBinding
import com.son.netflix.model.Film

class ComingSoonFragment : Fragment() {
    private var _binding: FragmentComingSoonBinding? = null
    private val binding get() = _binding!!
    private var films: List<Film>? = null
    private var filmAdapter: ComingSoonFilmAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentComingSoonBinding.inflate(inflater, container, false)
        val view = binding.root

        handleComingSoonFilm()

        return view
    }

    private fun handleComingSoonFilm() {
        films = listOf(
            Film(R.drawable.img_coming_soon_film_one, "Citation", R.raw.video),
            Film(R.drawable.img_coming_son_film_two, "Oloture", R.raw.video),
            Film(R.drawable.img_comming_soon_film_three, "The Setup", R.raw.video),
        )

        binding.rcFilm.apply {
            filmAdapter = ComingSoonFilmAdapter()
            filmAdapter?.setupData(films ?: listOf())

            adapter = filmAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}