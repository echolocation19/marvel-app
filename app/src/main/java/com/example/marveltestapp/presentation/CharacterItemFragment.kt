package com.example.marveltestapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.marveltestapp.databinding.FragmentCharacterItemBinding
import java.lang.RuntimeException

class CharacterItemFragment : Fragment() {

    private var _binding: FragmentCharacterItemBinding? = null
    private val binding: FragmentCharacterItemBinding
        get() = _binding ?: throw RuntimeException("FragmentCharacterItemBinding == null")

    private val charactersViewModelFactory by lazy {
        CharactersViewModelFactory(requireActivity().application)
    }

    private val charactersViewModel by lazy {
        ViewModelProvider(this, charactersViewModelFactory)[CharactersViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun getCharacterId(): Int =
        requireArguments().getInt(CHARACTER_ITEM_ID, CHARACTER_EMPTY_ID)

    companion object {

        private const val CHARACTER_ITEM_ID = "id"
        private const val CHARACTER_EMPTY_ID = 0

        @JvmStatic
        fun newInstance(characterId: Int) =
            CharacterItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(CHARACTER_ITEM_ID, characterId)
                }
            }
    }
}