package com.example.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscapp.databinding.ItemHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo, onSignSelected: (HoroscopeInfo) -> Unit) {
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        // Como no tenemos el contexto, tenemos que buscarlo dentro del binding, en alguna vista
        binding.tvSignTitle.text = binding.tvSignTitle.context.getString(horoscopeInfo.name)

        binding.clParent.setOnClickListener {
            startRotationAnimation(binding.ivHoroscope, newLambda = {onSignSelected(horoscopeInfo)})
            //onSignSelected(horoscopeInfo)
        }
    }

    // Antes, el setOnClickListener se hacía en el render, pero como queremos esta animación lo tenemos que configurar aquí.
    // Definimos una nueva función lambda: newLambda, sin parámetros, que lo que haga sea llamar a onSignSelected
    private fun startRotationAnimation(view: View, newLambda:()->Unit) {
        val apply = view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction { newLambda() }
            start()
        }
    }
}