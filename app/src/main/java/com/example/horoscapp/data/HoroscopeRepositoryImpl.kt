package com.example.horoscapp.data

import android.util.Log
import com.example.horoscapp.data.network.HoroscopeApiService
import com.example.horoscapp.data.network.response.PredictionResponse
import com.example.horoscapp.domain.HoroscopeRepository
import com.example.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

class HoroscopeRepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService):HoroscopeRepository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        // Ejecuta una tarea, es complejo
        runCatching {
            apiService.getHoroscope(sign)
        }.onSuccess {
            return it.toDomain()
        }.onFailure {
            Log.e("FMMP", "Ha ocurrido un error:\n $it")
        }
        return null
    }

}