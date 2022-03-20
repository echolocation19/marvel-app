package com.example.marveltestapp.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marveltestapp.databinding.FragmentCharacterItemBinding
import com.example.marveltestapp.domain.CharacterInfo
import com.example.marveltestapp.presentation.adapters.Comics
import com.example.marveltestapp.presentation.adapters.ComicsAdapter
import com.example.marveltestapp.presentation.viewmodel.CharacterViewModel
import com.example.marveltestapp.presentation.viewmodel.CharacterViewModelFactory
import com.squareup.picasso.Picasso


class CharacterItemFragment : Fragment() {

    private var _binding: FragmentCharacterItemBinding? = null
    private val binding: FragmentCharacterItemBinding
        get() = _binding ?: throw RuntimeException("FragmentCharacterItemBinding == null")

    private val characterViewModelFactory by lazy {
        CharacterViewModelFactory(requireActivity().application, getCharacterId())
    }

    private val characterViewModel by lazy {
        ViewModelProvider(this, characterViewModelFactory)[CharacterViewModel::class.java]
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
        characterViewModel.getCharacter().observe(viewLifecycleOwner) {
            setCharacterInfo(it)
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
            layoutParams.height = getRecyclerViewResolution()
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun getRecyclerViewResolution(): Int {
        val windowManager: WindowManager =
            context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics: WindowMetrics = windowManager.currentWindowMetrics
        return (metrics.bounds.bottom * 0.9).toInt()
    }

    private fun getCharacterId(): Int =
        requireArguments().getInt(CHARACTER_ITEM_ID, CHARACTER_EMPTY_ID)

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