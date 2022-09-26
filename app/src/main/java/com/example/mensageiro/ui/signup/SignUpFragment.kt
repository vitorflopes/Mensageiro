package com.example.mensageiro.ui.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.mensageiro.R
import com.example.mensageiro.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        binding.btnCadastrar.setOnClickListener {
            binding.pbLoadingSU.isVisible = true
            val nome = binding.etNomeCadastrar.text.toString()
            val email = binding.etEmailCadastro.text.toString()
            val senha = binding.etSenhaCadastro.text.toString()
            if (nome.isNullOrBlank() || email.isNullOrBlank() || senha.isNullOrBlank()) {
                binding.pbLoadingSU.isVisible = false
                Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            }
            else {
                viewModel.cadastrarUsuario(nome, email, senha)
            }
        }

        viewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
        }

        viewModel.msg.observe(viewLifecycleOwner) {
            binding.pbLoadingSU.isVisible = false
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        return view
    }
}