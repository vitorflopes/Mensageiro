package com.example.mensageiro.ui.grupo.editarGrupo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.set
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mensageiro.R
import com.example.mensageiro.databinding.FragmentEditarGrupoBinding

class EditarGrupoFragment : Fragment() {

    private var _binding: FragmentEditarGrupoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EditarGrupoViewModel
    private val argumentos by navArgs<EditarGrupoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarGrupoBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(EditarGrupoViewModel::class.java)

        val idGrupo = argumentos.idGrupo
        viewModel.retornarGrupo(idGrupo)

        viewModel.grupo.observe(viewLifecycleOwner) {
            binding.etNomeGrupoEditar.setText(it.nome)
            binding.etDescricaoGrupoEditar.setText(it.descricao)
        }

        binding.btnSalvarEdicao.setOnClickListener {
            val nomeGrupo = binding.etNomeGrupoEditar.text.toString()
            val descricaoGrupo = binding.etDescricaoGrupoEditar.text.toString()
            if (nomeGrupo.isNullOrBlank() || descricaoGrupo.isNullOrBlank()) {
                Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            }
            else {
                viewModel.salvarGrupo(idGrupo, nomeGrupo, descricaoGrupo)
            }
        }

        binding.btnVoltarEG.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
        }

        return view
    }
}