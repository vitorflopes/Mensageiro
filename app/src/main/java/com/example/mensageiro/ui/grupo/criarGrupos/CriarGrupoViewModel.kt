package com.example.mensageiro.ui.grupo.criarGrupos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mensageiro.dao.AuthDao
import com.example.mensageiro.dao.GrupoDao
import com.example.mensageiro.dao.UsuarioDao
import com.example.mensageiro.model.Grupo
import com.example.mensageiro.model.Usuario

class CriarGrupoViewModel : ViewModel() {

    val status = MutableLiveData<Boolean>()
    val msg = MutableLiveData<String>()

    init {
        status.value = false
    }

    fun cadastrarGrupo(grupo: Grupo) {
        val idUsusarioLogado = AuthDao.getCurrentUser()!!.uid

        grupo.listaIdParticipantes = mutableListOf(idUsusarioLogado)
        grupo.idLider = idUsusarioLogado
        GrupoDao.salvarGrupo(grupo).addOnSuccessListener {
            msg.value = it.id
            UsuarioDao.addIdGrupotoUsuario(idUsusarioLogado, it.id)
            status.value = true
        }.addOnFailureListener {
            msg.value = it.message
        }
    }
}