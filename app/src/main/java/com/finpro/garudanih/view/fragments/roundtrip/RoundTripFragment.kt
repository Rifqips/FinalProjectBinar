package com.finpro.garudanih.view.fragments.roundtrip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.finpro.garudanih.R
import com.finpro.garudanih.databinding.FragmentRoundTripBinding

class RoundTripFragment : Fragment() {

    private lateinit var binding : FragmentRoundTripBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRoundTripBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}