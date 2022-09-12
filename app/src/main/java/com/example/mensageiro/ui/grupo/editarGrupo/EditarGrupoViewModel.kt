package com.example.mensageiro.ui.grupo.editarGrupo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mensageiro.dao.GrupoDao
import com.example.mensageiro.model.Grupo

class EditarGrupoViewModel : ViewModel() {

    val grupo = MutableLiveData<Grupo>()
    val status = MutableLiveData<Boolean>()

    init {
        status.value = false
    }

    fun retornarGrupo(idGrupo: String) {
        val task = GrupoDao.exibirGrupo(idGrupo)
        task.addOnSuccessListener {
            grupo.value = it.toObjects(Grupo::class.java).first()
        }
    }

    fun salvarGrupo(idGrupo: String, nomeGrupo: String, decricaoGrupo: String) {
        GrupoDao.salvarAlteracaoGrupo(idGrupo, nomeGrupo, decricaoGrupo).addOnSuccessListener {
            status.value = true
        }
    }
}