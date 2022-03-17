package com.example.marveltestapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.marveltestapp.R
import com.example.marveltestapp.databinding.ActivityMainBinding
import com.example.marveltestapp.presentation.adapters.CharacterItemsAdapter
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
    get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private var _characterListAdapter: CharacterItemsAdapter? = null
    private val characterListAdapter: CharacterItemsAdapter
    get() = _characterListAdapter ?: throw RuntimeException("CharacterItemsAdapter == null")

    private val viewModelFactory by lazy {
        CharactersViewModelFactory(application)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CharactersViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupItemClick()
        viewModel.charactersList.observe(this) {
            characterListAdapter.submitList(it)
        }
    }

    private fun setupItemClick() {
        characterListAdapter.onCharacterClickListener = {
            if (isOnePaneMode()) {
                val intent = CharacterItemActivity.newInstance(this, it.characterId)
                startActivity(intent)
            } else {
                launchFragment(CharacterItemFragment.newInstance(it.characterId))
            }
        }
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.characterItemContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun isOnePaneMode() =
        binding.characterItemContainer == null

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