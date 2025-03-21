package com.example.horoscapp.domain.usecase

import com.example.horoscapp.domain.HoroscopeRepository
import javax.inject.Inject

/*
Casos de uso, con una función única, o una lógica única
Esta clase va inyectada en el ViewModel
 */
class GetPrediction @Inject constructor(private val repository:HoroscopeRepository) {
    // operator permite sobreescribir algunas de las funciones de la clase (¿de creación?)
    suspend operator fun invoke(sign:String) = repository.getPrediction(sign)
}