package com.example.horoscapp.data.network.response

import com.example.horoscapp.domain.model.PredictionModel
import com.google.gson.annotations.SerializedName

/*
{
  "date": "2020-01-01",
  "horoscope": "Hoy será un día asqueroso para ti.",
  "icon": "https://newastro.vercel.app/static/assets/zodiac-1.png",
  "id": 10,
  "sign": "aries"
}
 */
// Se pasa cada propiedad a cada parámetro del constructor en función de su nombre.
// También se suele utilizar @SerializedName, ya que si se ofusca el código el nombre de la variable cambiará.
//
data class PredictionResponse(
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("sign") val sign:String) {

    /*
     Función de extensión: agregan nueva funcionalidad sin modificar su código.
        1) No pueden acceder a miembros privados o protegidos de la clase extendida.
        2) No modifican realmente la clase original, solo agregan funcionalidad de manera externa.
        3) Si hay un método con el mismo nombre en la clase original, la función de extensión será ignorada

    Al final no se puede hacer con una función de extensión, falla porque está fuera de ámbito
    (creo)
     */
    fun toDomain():PredictionModel = PredictionModel(sign, horoscope)
}