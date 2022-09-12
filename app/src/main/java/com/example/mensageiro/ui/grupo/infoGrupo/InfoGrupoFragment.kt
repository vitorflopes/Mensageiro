package com.example.mensageiro.ui.grupo.infoGrupo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mensageiro.R
import com.example.mensageiro.databinding.FragmentInfoGrupoBinding

class InfoGrupoFragment : Fragment() {

    private var _binding: FragmentInfoGrupoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: InfoGrupoViewModel
    private val argumentos by navArgs<InfoGrupoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoGrupoBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(InfoGrupoViewModel::class.java)

        val idGrupo = argumentos.idGrupo
        viewModel.retornarGrupo(idGrupo)

        binding.btnAdicionarParticipanteInfo.setOnClickListener {
            val direcao = InfoGrupoFragmentDirections
                .actionInfoGrupoFragmentToAddParticipanteFragment(idGrupo)
            findNavController().navigate(direcao)
        }

        binding.btnAdicionarTarefaInfo.setOnClickListener {
            val direcao = InfoGrupoFragmentDirections
                .actionInfoGrupoFragmentToAdicionarTarefaFragment(idGrupo)
            findNavController().navigate(direcao)
        }

        viewModel.grupo.observe(viewLifecycleOwner) {
            binding.tvNomeGrupoInfo.text = it.nome.toString()
            binding.tvDescricaoGrupoInfo.text = it.descricao.toString()
        }

        return view
    }
}