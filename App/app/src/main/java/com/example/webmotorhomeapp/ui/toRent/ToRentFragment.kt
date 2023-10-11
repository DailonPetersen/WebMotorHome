package com.example.webmotorhomeapp.ui.toRent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.webmotorhomeapp.databinding.FragmentToRentBinding

class ToRentFragment : Fragment() {

    private lateinit var binding: FragmentToRentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToRentBinding.inflate(inflater, container, false)

        return binding.root
    }
}