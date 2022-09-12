package com.example.mensageiro.ui.addParticipante

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mensageiro.dao.GrupoDao
import com.example.mensageiro.dao.UsuarioDao
import com.example.mensageiro.model.Usuario

class AddParticipanteViewModel : ViewModel() {

    val status = MutableLiveData<Boolean>()

    init {
        status.value = false
    }

    fun adicionarParticipante(idGrupo: String, emailParticipante: String) {
        val task = UsuarioDao.exibirUsuarioByEmail(emailParticipante)
        task.addOnSuccessListener {
            val usuario = it.toObjects(Usuario::class.java).first()
            GrupoDao.addIdParticipanteToGrupo(usuario.id!!, idGrupo).addOnSuccessListener {
                UsuarioDao.addIdGrupotoUsuario(usuario.id, idGrupo).addOnSuccessListener {
                    status.value = true
                }
            }
        }
    }

}