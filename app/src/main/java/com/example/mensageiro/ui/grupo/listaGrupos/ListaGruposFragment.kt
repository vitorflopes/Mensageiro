package com.example.mensageiro.ui.grupo.listaGrupos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mensageiro.R
import com.example.mensageiro.adapter.GrupoAdapter
import com.example.mensageiro.databinding.FragmentListaGruposBinding

class ListaGruposFragment : Fragment() {

    private var _binding: FragmentListaGruposBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListaGruposViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaGruposBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(ListaGruposViewModel::class.java)

        binding.rvListaGrupo.layoutManager = LinearLayoutManager(context)
        binding.rvListaGrupo.setHasFixedSize(true)

        viewModel.retornarListaGrupo()

        viewModel.listaGrupo.observe(viewLifecycleOwner) {
            binding.tvNumGruposLG.text = it.size.toString()
            binding.rvListaGrupo.adapter = GrupoAdapter(it) { grupo ->
                val direcao = ListaGruposFragmentDirections
                    .actionListaGruposFragmentToInfoGrupoFragment(grupo.id!!)
                findNavController().navigate(direcao)
            }
        }

        binding.btnVoltarLG.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }
}