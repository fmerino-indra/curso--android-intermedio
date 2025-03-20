package com.example.horoscapp.ui.detail

import androidx.lifecycle.ViewModel
import com.example.horoscapp.ui.horoscope.HoroscopeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor():ViewModel() {
    /*
    Vamos a definir 3 estados: cargando, pantalla ha cargado ok o error
    Vamos a crear una clase para que controle estos estados
     */

    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state:StateFlow<HoroscopeDetailState> = _state
}