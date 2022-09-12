package com.example.mensageiro.ui.grupo.infoGrupo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mensageiro.dao.GrupoDao
import com.example.mensageiro.model.Grupo

class InfoGrupoViewModel : ViewModel() {

    val grupo = MutableLiveData<Grupo>()

    fun retornarGrupo(idGrupo: String) {
        val task = GrupoDao.exibirGrupo(idGrupo)
        task.addOnSuccessListener {
            grupo.value = it.toObjects(Grupo::class.java).first()
        }
    }
}