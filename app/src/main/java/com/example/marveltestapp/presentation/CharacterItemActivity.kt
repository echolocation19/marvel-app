package com.example.marveltestapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marveltestapp.R
import com.example.marveltestapp.databinding.ActivityCharacterItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterItemActivity : AppCompatActivity() {

    private val binding: ActivityCharacterItemBinding by lazy {
        ActivityCharacterItemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.characterItemContainer, CharacterItemFragment.newInstance(getCharacterId()))
                .commit()
        }
    }

    private fun getCharacterId(): Int =
        intent.getIntExtra(EXTRA_CHARACTER_ID, CHARACTER_EMPTY_ID)


    companion object {

        private const val EXTRA_CHARACTER_ID = "id"
        private const val CHARACTER_EMPTY_ID = 0

        fun newInstance(context: Context, characterId: Int): Intent {
            val intent = Intent(context, CharacterItemActivity::class.java)
            intent.putExtra(EXTRA_CHARACTER_ID, characterId)
            return intent
        }
    }

}