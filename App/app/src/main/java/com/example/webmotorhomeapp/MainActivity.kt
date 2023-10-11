package com.example.webmotorhomeapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.webmotorhomeapp.databinding.ActivityMainBinding
import com.example.webmotorhomeapp.ui.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import java.io.IOException


private val TAG = MainActivity::class.java.simpleName
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy { MainViewModel(this.application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)

        binding.navView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.action_navigation_to_homeFragment)
                }
                R.id.navigation_buy_fragment -> {
                    navController.navigate(R.id.action_navigation_to_navigation_buy_fragment)
                }
                R.id.navigation_account -> {
                    navController.navigate(R.id.action_navigation_to_myAccountFragment)
                }
                R.id.navigation_chat -> {
                    navController.navigate(R.id.action_navigation_to_chatFragment)
                }
                R.id.navigation_search -> {
                    navController.navigate(R.id.action_navigation_to_navigation_buy_fragment)
                }
                else -> false
            }
            true
        }
    }

    private fun replaceFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.navHostFragmentActivityMain.id, frag)
            .commit()
    }
}