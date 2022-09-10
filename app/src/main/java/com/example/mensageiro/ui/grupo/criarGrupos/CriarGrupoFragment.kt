package com.example.mensageiro.ui.grupo.criarGrupos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mensageiro.R
import com.example.mensageiro.databinding.FragmentCriarGrupoBinding
import com.example.mensageiro.model.Grupo

class CriarGrupoFragment : Fragment() {

    private var _binding: FragmentCriarGrupoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CriarGrupoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCriarGrupoBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(CriarGrupoViewModel::class.java)

        binding.btnCriarGrupoCriarGrupo.setOnClickListener {
            val nomeGrupo = binding.etNomeGrupoCriar.text.toString()
            val descricao = binding.etDescricaoGrupo.text.toString()
            val grupo = Grupo(null, nomeGrupo, descricao)
            viewModel.cadastrarGrupo(grupo)
        }

        viewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Grupo criado.", Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            }
        }

        return view
    }
}