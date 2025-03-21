package com.example.horoscapp.domain

import com.example.horoscapp.data.network.response.PredictionResponse
import com.example.horoscapp.domain.model.PredictionModel

/*
Para que funcione Retrofit, hay que marcar este "objeto" como inyectable, sin embargo, no se puede.
Para hacerlo hay que crear un Módulo, que es un "objeto" de Dagger que permite hacer cosas "avanzadas"
que se salen de lo común.

El objeto Retrofit no está marcado como @Inject. El módulo permite "proveer" dicho objeto.

 */
interface HoroscopeRepository {
    suspend fun getPrediction(sign:String): PredictionModel?
}