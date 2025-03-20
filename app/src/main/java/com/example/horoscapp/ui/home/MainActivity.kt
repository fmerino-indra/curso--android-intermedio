package com.example.horoscapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.horoscapp.R
import com.example.horoscapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

// Se prepara la clase para que pueda recibir cosas inyectadas
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initNavigation()
    }

    private fun initNavigation() {
        // Se hace Cast porque en activity_main.xml he puesto el atributo name al fragmen indicando este nombre (parece que actúa como tipo)
        val navHost = supportFragmentManager.findFragmentById(R.id.framentContainerView) as NavHostFragment
        navController = navHost.navController
        binding.bottomNavView.setupWithNavController(navController)
    }
}