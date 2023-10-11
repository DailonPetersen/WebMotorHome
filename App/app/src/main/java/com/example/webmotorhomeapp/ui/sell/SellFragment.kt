package com.example.webmotorhomeapp.ui.sell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.webmotorhomeapp.databinding.FragmentBaseTabBinding
import com.example.webmotorhomeapp.databinding.FragmentRentBinding
import com.example.webmotorhomeapp.databinding.FragmentSellBinding
import com.example.webmotorhomeapp.ui.BaseTabFragment
import com.example.webmotorhomeapp.ui.profile.CompleteProfileViewModel
import com.example.webmotorhomeapp.ui.toRent.ToRentFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab

class SellFragment: Fragment() {

    private lateinit var binding : FragmentSellBinding
    private lateinit var viewModel: CompleteProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSellBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[CompleteProfileViewModel::class.java]
    }
}