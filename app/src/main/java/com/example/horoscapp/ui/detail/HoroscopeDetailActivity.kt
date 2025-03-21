package com.example.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscapp.R
import com.example.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.model.HoroscopeModel.*
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
        // ???
        // args.sign

        // Antes de inicializar, incluso, la UI, podemos ya ir llamando a la carga de datos.
        initData()

        initUI()
    }

    private fun initData() {
        horoscopeDetailViewModel.getHoroscope(args.sign)
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            // Podríamos usar el startActivity o navigator, pero hay una forma más rápida
            // Coño, está deprecado
            onBackPressed()
        }
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
                            successState(it)
                        }
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun successState(state: HoroscopeDetailState.Success) {
        binding.pb.isVisible = false
        binding.tvDetailTitle.text = state.sign
        binding.tvDetailBody.text = state.prediction
        val img = when (state.horoscope) {
            Aries -> R.drawable.detail_aries
            Taurus -> R.drawable.detail_taurus
            Gemini -> R.drawable.detail_gemini
            Cancer -> R.drawable.detail_cancer
            Leo -> R.drawable.detail_leo
            Virgo -> R.drawable.detail_virgo
            Libra -> R.drawable.detail_libra
            Scorpio -> R.drawable.detail_scorpio
            Sagittarius -> R.drawable.detail_sagittarius
            Capricorn -> R.drawable.detail_capricorn
            Aquarius -> R.drawable.detail_aquarius
            Pisces -> R.drawable.detail_pisces
        }
        binding.ivDetailHoroscope.setImageResource(img)
    }

    private fun errorState() {
        binding.pb.isVisible = false
    }

}