package com.example.mensageiro.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mensageiro.dao.AuthDao
import com.example.mensageiro.dao.UsuarioDao
import com.example.mensageiro.model.Usuario

class SignUpViewModel : ViewModel() {

    val status = MutableLiveData<Boolean>()
    val msg = MutableLiveData<String>()

    init {
        status.value = false
    }

    fun cadastrarUsuario(nome: String, email: String, senha: String) {

        val task = AuthDao.cadastrarUsuario(email, senha)

        task.addOnSuccessListener {
            status.value = true
            val idUsuario = AuthDao.getCurrentUser()!!.uid
            val usuario = Usuario(idUsuario, nome, email)

            UsuarioDao.salvarUsuario(idUsuario, usuario)
        }.addOnFailureListener {
            msg.value = it.message
        }
    }
}