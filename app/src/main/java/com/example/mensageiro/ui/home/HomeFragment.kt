package com.example.mensageiro.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mensageiro.R
import com.example.mensageiro.dao.AuthDao
import com.example.mensageiro.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.retornaFeriado()

        binding.btnCriarUmGrupoHome.setOnClickListener {
            findNavController().navigate(R.id.criarGrupoFragment)
        }

        binding.btnGruposHome.setOnClickListener {
            findNavController().navigate(R.id.listaGruposFragment)
        }

        binding.btnLogout.setOnClickListener {
            viewModel.deslogar()
            val direction = HomeFragmentDirections.actionHomeFragmentToSignInFragment()
            findNavController().navigate(direction)
        }

        viewModel.usuario.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tvNomeHome.text = it.nome
            }
        }

        viewModel.feriado.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tvFeriadoH.text = it.name
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        val user = viewModel.retornaUsuarioLogado()
        if (user == null) {
            val direction = HomeFragmentDirections.actionHomeFragmentToSignInFragment()
            findNavController().navigate(direction)
        }
    }
}