package com.example.marveltestapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marveltestapp.R

class CharacterItemFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_item, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CharacterItemFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}