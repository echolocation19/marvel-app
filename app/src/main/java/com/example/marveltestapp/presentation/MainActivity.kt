package com.example.marveltestapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marveltestapp.R
import com.example.marveltestapp.databinding.ActivityMainBinding
import com.example.marveltestapp.presentation.adapters.CharacterItemsAdapter
import com.example.marveltestapp.presentation.viewmodel.CharactersViewModel
import com.example.marveltestapp.presentation.viewmodel.CharactersViewModelFactory
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

//    interface OnBackPressed {
//        fun onBackPressed()
//    }

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
                val intent = CharacterItemActivity.newInstance(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(CharacterItemFragment.newInstance(it.id))
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
        _characterListAdapter = CharacterItemsAdapter(applicationContext)
        with(binding.rvCharactersList) {
            adapter = characterListAdapter
            recycledViewPool.setMaxRecycledViews(
                CharacterItemsAdapter.VIEW_TYPE,
                CharacterItemsAdapter.MAX_POOL_SIZE
            )
        }
    }

//    override fun onBackPressed() {
//        notifyFragment()
//        super.onBackPressed()
//    }
//
//    private fun notifyFragment() {
//        val fragments = supportFragmentManager.fragments
//        for (f in fragments) {
//            if (f != null)
//                (f as CharacterItemFragment).onBackPressed()
//        }
//    }


}