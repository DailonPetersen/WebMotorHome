package com.example.webmotorhomeapp.ui.rent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.webmotorhomeapp.databinding.FragmentRentBinding

class RentFragment : Fragment() {

    private lateinit var binding: FragmentRentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRentBinding.inflate(inflater, container, false)

        return binding.root
    }

}