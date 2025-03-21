package com.example.horoscapp.ui.horoscope

import com.example.horoscapp.domain.model.HoroscopeModel

sealed class HoroscopeDetailState {
    data object Loading:HoroscopeDetailState()
    //data class porque hay que pasarle parámetros, es decir, que no habrá un sólo error.
    data class Error(val error:String):HoroscopeDetailState()

    // El valor sign es el recibido de back, por lo que nos podría fallar. Añadimos el enum para
    // luego comprobar qué imagen debemos presentar.
    data class Success(val sign: String, val prediction: String, val horoscope: HoroscopeModel):HoroscopeDetailState()

    /*
    Funcionamiento.
    - El ViewModel cargará uno de estos estados y para la vista es muy sencillo saber lo que tiene que pintar.
     */
}