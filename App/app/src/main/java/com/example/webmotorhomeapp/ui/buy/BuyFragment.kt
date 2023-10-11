package com.example.webmotorhomeapp.ui.buy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.webmotorhomeapp.databinding.FragmentBuyBinding

class BuyFragment: Fragment() {

    private lateinit var binding: FragmentBuyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuyBinding.inflate(inflater, container, false)

        return binding.root
    }

}