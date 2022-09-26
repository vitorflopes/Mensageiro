package com.example.mensageiro.ui.tarefa

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mensageiro.R
import com.example.mensageiro.databinding.FragmentAdicionarTarefaBinding
import com.example.mensageiro.model.Tarefa

class AdicionarTarefaFragment : Fragment() {

    private var _binding: FragmentAdicionarTarefaBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AdicionarTarefaViewModel
    private val argumentos by navArgs<AdicionarTarefaFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdicionarTarefaBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(AdicionarTarefaViewModel::class.java)

        binding.btnSalvarTarefaAdd.setOnClickListener {
            val nomeTarefa = binding.etNomeTarefaAdd.text.toString()
            val descricaoTarefa = binding.etDescricaoTarefaAdd.text.toString()
            if (nomeTarefa.isNullOrBlank() || descricaoTarefa.isNullOrBlank()) {
                Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            }
            else {
                val tarefa = Tarefa(null, nomeTarefa, descricaoTarefa)
                viewModel.adicionarTarefa(argumentos.idGrupo, tarefa)
            }
        }

        viewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Tarefa criada.", Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            }
        }

        binding.btnVoltarAT.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }
}