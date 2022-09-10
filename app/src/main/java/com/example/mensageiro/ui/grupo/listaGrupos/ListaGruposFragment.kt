package com.example.mensageiro.ui.grupo.listaGrupos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mensageiro.R

class ListaGruposFragment : Fragment() {

    companion object {
        fun newInstance() = ListaGruposFragment()
    }

    private lateinit var viewModel: ListaGruposViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lista_grupos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListaGruposViewModel::class.java)
        // TODO: Use the ViewModel
    }

}