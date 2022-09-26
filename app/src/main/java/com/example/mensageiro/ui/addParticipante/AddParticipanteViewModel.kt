package com.example.mensageiro.ui.addParticipante

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mensageiro.dao.GrupoDao
import com.example.mensageiro.dao.UsuarioDao
import com.example.mensageiro.model.Usuario
import com.google.firebase.firestore.ktx.toObjects

class AddParticipanteViewModel : ViewModel() {

    val status = MutableLiveData<Boolean>()

    init {
        status.value = false
    }

    fun adicionarParticipante(idGrupo: String, emailParticipante: String) {
        val task = UsuarioDao.exibirUsuarioByEmail(emailParticipante)
        task.addOnSuccessListener {
            if (it.toObjects(Usuario::class.java).size != 0) {
                val usuario = it.toObjects(Usuario::class.java).first()
                if (!usuario.listaIdGrupos!!.contains(idGrupo)) {
                    val usuario = it.toObjects(Usuario::class.java).first()
                    GrupoDao.addIdParticipanteToGrupo(usuario.id!!, idGrupo).addOnSuccessListener {
                        UsuarioDao.addIdGrupotoUsuario(usuario.id, idGrupo).addOnSuccessListener {
                            status.value = true
                        }
                    }
                }
            }
        }
    }

}