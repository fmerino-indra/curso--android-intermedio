package com.example.horoscapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscapp.R
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoroscopeAdapter (private var horoscopeList: List<HoroscopeInfo> = emptyList(),
    private val onSignSelected:(HoroscopeInfo) -> Unit): RecyclerView.Adapter<HoroscopeViewHolder>() {
    // Crea y devuelve el viewHolder (inflándolo)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        return HoroscopeViewHolder(view)
    }

    override fun getItemCount() = horoscopeList.size

    // Se encarga de decirle al viewHolder lo que tiene que pintar
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(horoscopeList[position], onSignSelected)
    }

    fun updateList(list:List<HoroscopeInfo>){
        horoscopeList = list
        notifyDataSetChanged()
    }
}