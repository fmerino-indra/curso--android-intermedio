package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.databinding.FragmentHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [HoroscopeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HoroscopeFragment : Fragment() {
    // Lo llama delegado, función delegada ???
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    private lateinit var horoscopeAdapter:HoroscopeAdapter

    // Esto es para crear la vista, pero no para asociar el ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    // Este para el ViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initAdapter()
        initUIState()
    }

    private fun initAdapter() {
        // Toast saca un minimensaje en pantalla, como una notificación
        // Cuando HoroscopeViewHolder va a invocar la función recibida, realmente la asigna al listener onClick
        // Le está pasando un parámetro actual. Esto es la definición, kotlin lo llama it (item)
        // creo que es como si hiciéramos:
        /*
        onSignSelected ( miVar -> {
            getString(miVar.name
        }
         */

/**/
        // Con named argument (onSignSelected) y parámetros explícitos y con lambda dentro de los paréntesis pq es el último parámetro
        horoscopeAdapter = HoroscopeAdapter( onSignSelected = { signo ->
            println("$signo")
            println("Hola")
        })

        // Con named argument (onSignSelected) y sin parámetros explícitos y con lambda dentro de los paréntesis pq es el último parámetro
        horoscopeAdapter = HoroscopeAdapter( onSignSelected = {
            println("$it")
            println("Hola")
        })

        // "Last Parameter Call Syntax" o "Trailing Lambda Syntax": mover una lambda fuera de los paréntesis de una función o constructor si es el último parámetro
        // Sin named argument (onSignSelected) y parámetros explícitos y con lambda fuera de los paréntesis pq es el último parámetro
        horoscopeAdapter = HoroscopeAdapter() { signo ->
            println("$signo")
            println("Hola")
        }
        // Sin named argument (onSignSelected), parámetros explícitos y con lambda fuera de los paréntesis: se pueden eliminar los paréntesis
        horoscopeAdapter = HoroscopeAdapter { signo ->
            println("$signo")
            println("Hola")
        }

        // Si son más de un parámetro:
        /*
        val ha = HA { signo, numero ->
            println("Signo: $signo, Número: $numero")
        }
         */



        horoscopeAdapter = HoroscopeAdapter(onSignSelected = {
            Toast.makeText(context,getString(it.name), Toast.LENGTH_LONG).show()
            val sign = when(it) {
                HoroscopeInfo.Aquarius -> HoroscopeModel.Aquarius
                HoroscopeInfo.Aries -> HoroscopeModel.Aries
                HoroscopeInfo.Cancer -> HoroscopeModel.Cancer
                HoroscopeInfo.Capricorn -> HoroscopeModel.Capricorn
                HoroscopeInfo.Gemini -> HoroscopeModel.Gemini
                HoroscopeInfo.Leo -> HoroscopeModel.Leo
                HoroscopeInfo.Libra -> HoroscopeModel.Libra
                HoroscopeInfo.Pisces -> HoroscopeModel.Pisces
                HoroscopeInfo.Sagittarius -> HoroscopeModel.Sagittarius
                HoroscopeInfo.Scorpio -> HoroscopeModel.Scorpio
                HoroscopeInfo.Taurus -> HoroscopeModel.Taurus
                HoroscopeInfo.Virgo -> HoroscopeModel.Virgo
            }
            findNavController().navigate(
                // Esta clase es autogenerada por NavArgs
                // El método se crea cuando se hace el enganche en el graph. También se ha añadido un argumento que se pasa aquí, y se recibe en el activity
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(sign)
            )
        }) // Lista vacía (por defecto y función lambda

        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }
        // Lo anterior es la forma corta para no repetir binding.rvHoroscope ¿programación funcional?
        //binding.rvHoroscope.layoutManager = LinearLayoutManager(context)
        //binding.rvHoroscope.adapter = adapter
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Siempre que se modifique _horoscope.value (en el viewmodel) se invoca este método
                horoscopeViewModel.horoscope.collect{
                    // Cambios en la lista
                    horoscopeAdapter.updateList(it)
                    Log.i("FMMP", it.toString()) // it -> es la lista que recibe collect
                }
            }
        }
    }
}