package com.example.webmotorhomeapp.ui.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webmotorhomeapp.R
import com.example.webmotorhomeapp.data.anuncio.Anuncio
import com.example.webmotorhomeapp.data.motorhome.Motorhome
import com.example.webmotorhomeapp.databinding.FragmentHomeBinding
import com.example.webmotorhomeapp.ui.anuncios.AnunciosAdapter
import com.example.webmotorhomeapp.ui.anuncios.AnunciosChildAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController

    private val homeViewModel: HomeViewModel by viewModels()

    private var adapterParent: AnunciosAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        navController = findNavController()
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.fetchItems()

//        var listAnunciosColleted: List<Anuncio>? = null
//        var listMotorhomeColleted: List<Motorhome>? = null

        lifecycleScope.launch(Dispatchers.Main) {
            binding.root.viewTreeObserver.addOnPreDrawListener {
                return@addOnPreDrawListener true
            }

            homeViewModel.anuncios.collect {
                it?.let {

                    for(anuncio in it) {
                        when (anuncio.idMotorhome) {
                            1 -> anuncio.images = setImagesInMotorhomes("kombi")
                            2 -> anuncio.images = setImagesInMotorhomes("Sprinter")
                            3 -> anuncio.images = setImagesInMotorhomes("master")
                        }
                    }
                    adapterParent = AnunciosAdapter(it)
                    binding.homeRecyclerView.adapter = adapterParent
                }
            }
        }
        binding.homeRecyclerView.adapter = adapterParent
        binding.homeRecyclerView.layoutManager = layoutManager

        onClickListeners()
        return binding.root
    }

    private fun setImagesInMotorhomes(value: String): List<Int> {
        if (value.contains("kombi")) {
            return listOf<Int>(R.drawable.kombi1, R.drawable.kombi, R.drawable.kombi2 )
        } else if (value.contains("Master")) {
            return listOf<Int>(R.drawable.van, R.drawable.van1, R.drawable.van2 )
        } else if (value.contains("Sprinter")) {
            return listOf<Int>(R.drawable.sprinter, R.drawable.sprinter1, R.drawable.sprinter2 )
        }
        return listOf()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun onClickListeners() {
        binding.compreButton.setOnClickListener {
            navController.navigate(R.id.action_navigation_home_to_navigation_buy_fragment)
        }
        binding.venderButton.setOnClickListener {
            navController.navigate(R.id.action_navigation_home_to_navigation_sell_fragment)
        }
        binding.alugarButton.setOnClickListener {
            navController.navigate(R.id.action_navigation_home_to_navigation_rent_fragment)
        }
    }
}