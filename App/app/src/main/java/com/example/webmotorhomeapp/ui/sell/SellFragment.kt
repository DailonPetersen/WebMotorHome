package com.example.webmotorhomeapp.ui.sell

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.webmotorhomeapp.data.anuncio.Anuncio
import com.example.webmotorhomeapp.data.motorhome.Motorhome
import com.example.webmotorhomeapp.databinding.FragmentBaseTabBinding
import com.example.webmotorhomeapp.databinding.FragmentRentBinding
import com.example.webmotorhomeapp.databinding.FragmentSellBinding
import com.example.webmotorhomeapp.network.WebMotorhomeModule
import com.example.webmotorhomeapp.ui.BaseTabFragment
import com.example.webmotorhomeapp.ui.home.HomeViewModel
import com.example.webmotorhomeapp.ui.profile.CompleteProfileViewModel
import com.example.webmotorhomeapp.ui.toRent.ToRentFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.random.Random

class SellFragment: Fragment() {

    private lateinit var binding : FragmentSellBinding
    private val sellViewModel: SellViewModel by viewModels()

    private val service = WebMotorhomeModule.serviceImage()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSellBinding.inflate(inflater, container, false)

        binding.buttonCriarAnuncio.setOnClickListener {
            val motorhomeToCreate = Motorhome(
                modelo = binding.modelo.text.toString(),
                descricao = binding.descricao.text.toString(),
                fabricante = binding.fabricante.text.toString(),
                ano = binding.ano.text.toString().toInt(),
                exposicao = false,
                placa = binding.placa.toString(),
                avaliacao = 0,
                listImages = null
            )

            val motorhomeCreated = sellViewModel.createMotorhome(motorhomeToCreate)

            val anuncioToCreate = Anuncio(
                idMotorhome = motorhomeCreated?.id ?: 4,
                idCriador = Random.nextInt(),
                precoVenda = binding.preco.text.toString().toDouble(),
                disponivelParaAluguel = false,
                descricao = motorhomeCreated?.descricao ?: ""
            )


            sellViewModel.createAnuncio(anuncioToCreate)

        }

        //onClickImageButton()

        return binding.root
    }

//    private fun onClickImageButton(callback: (image: Uri) -> Unit) {
//
//        binding.addImagesButton.setOnClickListener {
//            val imagesIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            activityImageLauch(imagesIntent) {
//
//            }
//        }
//    }

    private fun activityImageLauch(callback: (image: Uri) -> Unit) = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            callback(it.data as Uri)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            sellViewModel.anuncios.collect {
                it?.let {
                    for (item in it){
                        Log.e("DAILON", "item $item")
                    }
                }
            }
        }
    }


}