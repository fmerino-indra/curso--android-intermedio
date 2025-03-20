package com.example.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavArgs
import androidx.navigation.navArgs
import com.example.horoscapp.R
import com.example.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.example.horoscapp.ui.horoscope.HoroscopeDetailState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHoroscopeDetailBinding

    /*
     Dos formas de definirlo:
     Se define la variable con tipo
     Se define el tipo del delegado <>
     */
//    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()
    private val horoscopeDetailViewModel by viewModels<HoroscopeDetailViewModel>()

    private val args:HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // args.sign

        initUI()
    }

    private fun initUI() {
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when(it){
                        is HoroscopeDetailState.Error -> {
                            errorState()
                        }
                        HoroscopeDetailState.Loading -> {
                            loadingState()
                        }
                        is HoroscopeDetailState.Success -> {
                            successState()
                        }
                    }
                }
            }
        }
    }

    private fun successState() {
        binding.pb.isVisible = false
    }

    private fun errorState() {

    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }
}