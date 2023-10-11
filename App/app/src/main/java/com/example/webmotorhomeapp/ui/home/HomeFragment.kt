package com.example.webmotorhomeapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.webmotorhomeapp.WebMotorhomeApplication
import com.example.webmotorhomeapp.data.user.User
import com.example.webmotorhomeapp.databinding.FragmentHomeBinding
import com.example.webmotorhomeapp.network.WebMotorhomeModule
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController
    val apiService by lazy { WebMotorhomeModule.service() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel = HomeViewModel(WebMotorhomeApplication.apiService)

        navController = findNavController()
        binding = FragmentHomeBinding.inflate(inflater, container, false)

//        val user = homeViewModel.getUsers()
//        Log.e("DAILON", "${user.value}");
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val response = try {
                apiService.getAllUsers()
            }catch (e: IOException) {
                throw IOException("Erro ao tentar buscar")
            }

            try {
                response.enqueue(object : Callback<List<User>> {
                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        if (response.isSuccessful) {
                            // Handle a successful response
                            val data = response.body()
                            Log.e("TAG", "data $data")
                        } else {
                            val data = response.errorBody()
                            Log.e("TAG", "data $data")
                        }
                    }

                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        Log.e("TAG", "error")
                    }
                })
            } catch (e: IOException) {
                throw IOException("Erro ao tentar conectar")
            }

        }
    }

//    private fun onClickListeners() {
//        binding.compreButton.setOnClickListener {
//            navController.navigate(R.id.action_navigation_home_to_navigation_buy_fragment)
//        }
//        binding.venderButton.setOnClickListener {
//            navController.navigate(R.id.action_navigation_home_to_navigation_sell_fragment)
//        }
//        binding.alugarButton.setOnClickListener {
//            navController.navigate(R.id.action_navigation_home_to_navigation_rent_fragment)
//        }
//    }
}