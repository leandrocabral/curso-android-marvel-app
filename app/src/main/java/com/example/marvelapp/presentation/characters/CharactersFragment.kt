package com.example.marvelapp.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.R
import com.example.core.domain.model.Character
import com.example.marvelapp.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding get() = _binding!!

    private val characterAdapter = CharacterAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharactersBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharacterAdapter()

        characterAdapter.submitList(
            listOf(
                Character(
                    "3D-Man",
                    "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
                ),
                Character(
                    "3D-Man",
                    "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
                ),
                Character(
                    "3D-Man",
                    "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
                ),
                Character(
                    "3D-Man",
                    "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
                )
            )
        )
    }

    private fun initCharacterAdapter() {
        with(binding.recyclerCharacter) {
            setHasFixedSize(true)
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}