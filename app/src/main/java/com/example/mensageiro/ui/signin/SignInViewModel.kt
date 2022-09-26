package com.example.mensageiro.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mensageiro.dao.AuthDao
import com.example.mensageiro.dao.UsuarioDao
import com.google.firebase.auth.FirebaseUser

class SignInViewModel : ViewModel() {

    val status = MutableLiveData<Boolean>()
    val msg = MutableLiveData<String>()

    init {
        status.value = false
    }

    fun autenticar (email: String, senha: String) {
        val task = AuthDao.validarUsuario(email, senha)

        task.addOnSuccessListener {
            status.value = true
        }.addOnFailureListener {
            msg.value = it.message
        }
    }

    fun retornaUsuarioLogado(): FirebaseUser? {
        return AuthDao.getCurrentUser()
    }

    fun salvaUserFacebook() {
        val user = AuthDao.getCurrentUser()
        UsuarioDao.salvaUserFacebook(user!!.uid, user.displayName!!, user.email!!).addOnSuccessListener {
            status.value = true
        }.addOnFailureListener {
            msg.value = it.message
        }
    }
}