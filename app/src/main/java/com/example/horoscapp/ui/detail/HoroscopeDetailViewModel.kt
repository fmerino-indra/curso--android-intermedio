package com.example.horoscapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.usecase.GetPrediction
import com.example.horoscapp.ui.horoscope.HoroscopeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPrediction: GetPrediction)
    :ViewModel() {
    /*
    Vamos a definir 3 estados: cargando, pantalla ha cargado ok o error
    Vamos a crear una clase para que controle estos estados
     */

    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state:StateFlow<HoroscopeDetailState> = _state

    lateinit var horoscope:HoroscopeModel

    fun getHoroscope(sign: HoroscopeModel) {
        // Conservamos el valor pasado como parámetro (el enum) para que luego podamos decidir
        // qué imagen presentamos. Esta info nunca se deja en el activity

        horoscope = sign
        // Como getPrection es suspend, hay que ejecutarlo desde una corrutina. Para eso
        // utilizamos viewModelScope.launch {...}

        // Para no saturar el hilo principal (el encargado de la UI), lanzamos esta corrutina en
        // el hilo IO (Dispatcher.IO)
        // Si lo ponemos con withContext, ejecuta en el hilo IO lo que halla dentro de las {}
        // Si lo ponemos así:
        //         viewModelScope.launch(Dispatchers.IO) {...}
        // Ejecuta todo en el hilo IO
        viewModelScope.launch {
            // Lo que va aquí lo ejecuta en el hilo principal
            _state.value = HoroscopeDetailState.Loading

            // LO que va dentro lo ejecuta en el hilo IO
            val result = withContext(Dispatchers.IO) {
                // Al hacer esto, no sólo estoy creando la instancia, sino que estoy también llamando al
                // cuerpo de la función sobrescrita "invoke"
                getPrediction(sign.name)
            }

            // Lo que va a quí, también en el principal
            if (result!=null) {
                _state.value = HoroscopeDetailState.Success(result.sign, result.prediction, horoscope)
            } else {
                _state.value = HoroscopeDetailState.Error("[FMMP] Ha ocurrido un error")
            }
        }
    }

}