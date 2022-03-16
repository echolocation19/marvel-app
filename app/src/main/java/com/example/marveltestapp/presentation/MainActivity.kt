package com.example.marveltestapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.marveltestapp.databinding.ActivityMainBinding
import com.example.marveltestapp.domain.Character
import com.example.marveltestapp.presentation.adapters.CharacterItemsAdapter
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
    get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private var _characterListAdapter: CharacterItemsAdapter? = null
    private val characterListAdapter: CharacterItemsAdapter
    get() = _characterListAdapter ?: throw RuntimeException("CharacterItemsAdapter == null")

    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        charactersViewModel.shopListLiveData.observe(this, Observer {
            characterListAdapter.submitList(it)
        })
        // access to key
        //BuildConfig.API_PRIVATE_KEY
    }

    private fun setupRecyclerView() {
        _characterListAdapter = CharacterItemsAdapter()
        with(binding.rvCharactersList) {
            adapter = characterListAdapter
            recycledViewPool.setMaxRecycledViews(
                CharacterItemsAdapter.VIEW_TYPE,
                CharacterItemsAdapter.MAX_POOL_SIZE
            )
        }
    }
}