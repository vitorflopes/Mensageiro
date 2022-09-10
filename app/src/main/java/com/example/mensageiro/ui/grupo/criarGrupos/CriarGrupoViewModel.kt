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

    init {
        status.value = false
    }

    fun cadastrarGrupo(grupo: Grupo) {

        val idUsusarioLogado = AuthDao.getCurrentUser()!!.uid
        val task = UsuarioDao.exibirUsuario(idUsusarioLogado)
        task.addSnapshotListener { snapshot, error ->
            if (snapshot != null) {
                val usuarioLogado = snapshot.toObjects(Usuario::class.java).first()
                grupo.listaParticipantes = mutableListOf(usuarioLogado)
                grupo.lider = usuarioLogado
                GrupoDao.salvarGrupo(grupo)
                status.value = true
            }
        }
    }
}