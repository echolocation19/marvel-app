package com.example.marveltestapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.marveltestapp.databinding.FragmentCharacterItemBinding
import com.example.marveltestapp.domain.CharacterInfo
import com.example.marveltestapp.presentation.adapters.Comics
import com.example.marveltestapp.presentation.adapters.ComicsAdapter
import com.example.marveltestapp.presentation.viewmodel.CharacterViewModel
import com.example.marveltestapp.presentation.viewmodel.UiState
import com.squareup.picasso.Picasso

class CharacterItemFragment : Fragment() {

    private var _binding: FragmentCharacterItemBinding? = null
    private val binding: FragmentCharacterItemBinding
        get() = _binding ?: throw RuntimeException("FragmentCharacterItemBinding == null")

    private val characterViewModel: CharacterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterViewModel.getCharacter(getCharacterId())
        characterViewModel.uiState().observe(viewLifecycleOwner) { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        }
    }

    private fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {  }
            is UiState.Success -> {
                setCharacterInfo(uiState.characterInfo)
                println(uiState.characterInfo.name)
            }
            is UiState.Error -> {
                Toast.makeText(context, uiState.message, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun setCharacterInfo(character: CharacterInfo) {
        with(binding) {
            tvName.text = character.name
            Picasso.get().load(character.thumbnail)
                .resize(IMAGE_SIZE, IMAGE_SIZE)
                .into(ivSuperhero)
        }
        setupRecyclerView(character.comicsUri)
    }

    private fun setupRecyclerView(comicsList: List<String>) {
        val modelList = comicsList.map {
            Comics(content = it)
        }
        val mAdapter = ComicsAdapter(modelList)
        with(binding.rvComics) {
            adapter = mAdapter
            recycledViewPool.setMaxRecycledViews(
                ComicsAdapter.VIEW_TYPE,
                ComicsAdapter.MAX_POOL_SIZE
            )
        }
    }

    private fun getCharacterId(): Int {
        return requireArguments().getInt(CHARACTER_ITEM_ID, CHARACTER_EMPTY_ID)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val IMAGE_SIZE = 100
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