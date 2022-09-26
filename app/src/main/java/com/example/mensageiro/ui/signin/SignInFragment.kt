package com.example.mensageiro.ui.signin

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mensageiro.R
import com.example.mensageiro.databinding.FragmentSignInBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var callbackManager: CallbackManager? = null
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        callbackManager = CallbackManager.Factory.create()
        auth = Firebase.auth
        binding.loginButton.setPermissions("email", "public_profile")
        binding.loginButton.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException) {
                Snackbar.make(view, error.message.toString(), Snackbar.LENGTH_LONG).show()
            }
        })

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val senha = binding.etSenha.text.toString()
            viewModel.autenticar(email, senha)
        }

        binding.tvBtnCadastrar.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }

        viewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.homeFragment)
            }
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModel.salvaUserFacebook()
                } else {
                    Toast.makeText(requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = viewModel.retornaUsuarioLogado()
        if (currentUser != null) {
            val direction = SignInFragmentDirections.actionSignInFragmentToHomeFragment()
            findNavController().navigate(direction)
        }
    }
}