package com.example.webmotorhomeapp.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.webmotorhomeapp.R
import com.example.webmotorhomeapp.databinding.FragmentCompleteProfileBinding

class CompleteProfileFragment : Fragment() {

    private lateinit var binding : FragmentCompleteProfileBinding
    private lateinit var viewModel: CompleteProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentCompleteProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[CompleteProfileViewModel::class.java]
    }

}