package com.example.mensageiro.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mensageiro.dao.AuthDao
import com.example.mensageiro.dao.UsuarioDao
import com.example.mensageiro.model.Usuario

class HomeViewModel : ViewModel() {

    val usuario = MutableLiveData<Usuario>()

    init {
        val idUsuarioLogado = AuthDao.getCurrentUser()!!.uid

        val task = UsuarioDao.exibirUsuario(idUsuarioLogado)
        task.addOnSuccessListener {
            usuario.value = it.toObjects(Usuario::class.java).first()
        }
    }

    fun deslogar() {
        AuthDao.deslogar()
    }
}