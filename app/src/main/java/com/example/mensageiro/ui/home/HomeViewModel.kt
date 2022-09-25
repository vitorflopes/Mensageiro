package com.example.mensageiro.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mensageiro.dao.AuthDao
import com.example.mensageiro.dao.UsuarioDao
import com.example.mensageiro.model.Feriado
import com.example.mensageiro.model.Usuario
import com.example.mensageiro.service.FeriadoServices
import com.example.mensageiro.service.RetroFit
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel : ViewModel() {

    val usuario = MutableLiveData<Usuario>()
    val feriado = MutableLiveData<Feriado>()

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

    fun retornaFeriado() {
        val dataHoje = SimpleDateFormat("dd-MM-yyyy").format(Date())
        viewModelScope.launch {
            val feriados = RetroFit.feriadoServices().getFeriados()
            for (frd in feriados) {
                if (frd.date == dataHoje) {
                    feriado.value = frd
                }
            }
        }
    }

    fun retornaUsuarioLogado(): FirebaseUser? {
        return AuthDao.getCurrentUser()
    }
}