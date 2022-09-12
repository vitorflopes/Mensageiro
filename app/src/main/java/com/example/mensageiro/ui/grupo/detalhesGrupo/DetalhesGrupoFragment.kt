package com.example.mensageiro.ui.grupo.detalhesGrupo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mensageiro.R

class DetalhesGrupoFragment : Fragment() {

    private lateinit var viewModel: DetalhesGrupoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detalhes_grupo, container, false)
    }
}