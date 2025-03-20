package com.example.horoscapp.ui.horoscope

sealed class HoroscopeDetailState {
    data object Loading:HoroscopeDetailState()
    //data class porque hay que pasarle parámetros, es decir, que no habrá un sólo error.
    data class Error(val error:String):HoroscopeDetailState()
    data class Success(val data:String):HoroscopeDetailState()

    /*
    Funcionamiento.
    - El ViewModel cargará uno de estos estados y para la vista es muy sencillo saber lo que tiene que pintar.
     */
}