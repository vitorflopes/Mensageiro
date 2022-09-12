package com.example.mensageiro.ui.addParticipante

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mensageiro.R
import com.example.mensageiro.databinding.FragmentAddParticipanteBinding

class AddParticipanteFragment : Fragment() {

    private var _binding: FragmentAddParticipanteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddParticipanteViewModel
    private val argumentos by navArgs<AddParticipanteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddParticipanteBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(AddParticipanteViewModel::class.java)

        binding.btnAdicionarParticipanteAdd.setOnClickListener {
            val emailParticipante = binding.etEmailNovoParticipante.text.toString()
            viewModel.adicionarParticipante(argumentos.idGrupo, emailParticipante)
        }

        viewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
        }

        return view
    }
}