package com.example.horoscapp.ui.luck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [LuckFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}