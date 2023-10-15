package com.example.webmotorhomeapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.webmotorhomeapp.R
import com.example.webmotorhomeapp.databinding.FragmentMyAccountBinding
import kotlinx.coroutines.launch

class MyAccountFragment : Fragment() {

    private lateinit var binding: FragmentMyAccountBinding
    private val viewModel: CompleteProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyAccountBinding.inflate(inflater, container, false)
        viewModel.fetchItems()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.users.collect {
                it?.let {
                    binding.nameUser.text = it[0].nome
                    binding.lastname.text = it[0].sobrenome
                    binding.email.text = it[0].email
                    binding.telefone.text = it[0].telefone
                }
            }
        }
    }


}