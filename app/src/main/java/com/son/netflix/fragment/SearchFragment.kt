package com.son.netflix.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.son.netflix.R
import com.son.netflix.activity.PlayActivity
import com.son.netflix.adapter.SearchFilmAdapter
import com.son.netflix.databinding.FragmentSearchBinding
import com.son.netflix.model.Film
import java.util.Locale

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var filmAdapter: SearchFilmAdapter? = null
    private var films: List<Film>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        handlerFilmTopSearch()
        setUpListener()

        return view
    }

    private fun setUpListener() {
        binding.tvMic.setOnClickListener(View.OnClickListener {
            promptSpeechInput()
        })
    }

    private fun handlerFilmTopSearch() {
        films = listOf(
            Film(R.drawable.image_one, "Citation", R.raw.video),
            Film(R.drawable.image_two, "Oloture", R.raw.video),
            Film(R.drawable.image_three, "The Setup", R.raw.video),
            Film(R.drawable.image_four, "Breaking Bad", R.raw.video),
            Film(R.drawable.image_five, "Ozark", R.raw.video),
            Film(R.drawable.image_six, "The Governor", R.raw.video),
            Film(R.drawable.image_seven, "You Excellency", R.raw.video),
            Film(R.drawable.image_seven, "You Excellency", R.raw.video),
            Film(R.drawable.image_seven, "You Excellency", R.raw.video),
            Film(R.drawable.image_seven, "You Excellency", R.raw.video),
            Film(R.drawable.image_seven, "You Excellency", R.raw.video),
            Film(R.drawable.image_seven, "You Excellency", R.raw.video),
            Film(R.drawable.image_seven, "You Excellency", R.raw.video),
            Film(R.drawable.image_seven, "You Excellency", R.raw.video),
            Film(R.drawable.image_seven, "You Excellency", R.raw.video),
            Film(R.drawable.image_seven, "You Excellency", R.raw.video),
            )

        binding.rvFilm.apply {
            val layoutParam = LinearLayoutManager(requireContext())
            layoutManager = layoutParam
            filmAdapter = SearchFilmAdapter()
            filmAdapter?.setupData(films ?: listOf())
            adapter = filmAdapter

            binding.etSearch.doOnTextChanged { text, start, before, count ->
                filmAdapter?.filter?.filter(text.toString())
            }

            filmAdapter?.onClickItem = {
                val intent = Intent(requireContext(), PlayActivity::class.java)

                intent.putExtra("key", it)
                startActivity(intent)
            }
        }
    }

    private fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt))
        try {

            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                getString(R.string.speech_not_supported),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == AppCompatActivity.RESULT_OK && null != data) {
                    val result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    binding.etSearch.setText(result!![0])
                }
            }
        }
    }

    companion object {
        private const val REQ_CODE_SPEECH_INPUT = 100
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}