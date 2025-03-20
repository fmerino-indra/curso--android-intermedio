package com.example.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.example.horoscapp.data.HoroscopeProvider
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
// Le inyectamos el horoscopeProvider
// No es necesario crear el argumento del constructor como variable (var o val) ya que desde el init se puede acceder
// Si necesitáramos acceder desde otro lado, habría que declararla como var o val

class HoroscopeViewModel @Inject constructor(horoscopeProvider: HoroscopeProvider) : ViewModel() {
    // Flows
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    // Creamos una variable no mutable para que desde fuera no se pueda modificar.
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    // Este método se invoca cuando se cree el viewModel
    init {
        _horoscope.value = horoscopeProvider.getHoroscopes()
    }
}