package com.example.horoscapp.data

import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.domain.model.HoroscopeInfo.Aquarius
import com.example.horoscapp.domain.model.HoroscopeInfo.Aries
import com.example.horoscapp.domain.model.HoroscopeInfo.Cancer
import com.example.horoscapp.domain.model.HoroscopeInfo.Capricorn
import com.example.horoscapp.domain.model.HoroscopeInfo.Gemini
import com.example.horoscapp.domain.model.HoroscopeInfo.Leo
import com.example.horoscapp.domain.model.HoroscopeInfo.Libra
import com.example.horoscapp.domain.model.HoroscopeInfo.Pisces
import com.example.horoscapp.domain.model.HoroscopeInfo.Sagittarius
import com.example.horoscapp.domain.model.HoroscopeInfo.Scorpio
import com.example.horoscapp.domain.model.HoroscopeInfo.Taurus
import com.example.horoscapp.domain.model.HoroscopeInfo.Virgo
import javax.inject.Inject


class HoroscopeProvider @Inject constructor() {
    fun getHoroscopes():List<HoroscopeInfo> {
        return listOf(
            Aries, Taurus, Gemini,
            Cancer, Leo, Virgo,
            Libra, Scorpio, Sagittarius,
            Capricorn, Aquarius, Pisces
        )
    }
}