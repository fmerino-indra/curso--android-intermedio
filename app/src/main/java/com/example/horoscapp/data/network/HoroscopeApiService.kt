package com.example.horoscapp.data.network

import com.example.horoscapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApiService {
    // Como es una petición hay que hacerlo en una corrutina. Las corrutinas deben definirse en funciones suspendidas
    // La etiqueta de @Path debe coincidir con el patrón de @GET. La variable puede llamarse de cualquier forma
    @GET("/{sign}")
    suspend fun getHoroscope(@Path("sign") sign: String): PredictionResponse
}