package com.example.mensageiro.ui.grupo.infoGrupo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mensageiro.dao.AuthDao
import com.example.mensageiro.dao.GrupoDao
import com.example.mensageiro.dao.TarefaDao
import com.example.mensageiro.dao.UsuarioDao
import com.example.mensageiro.model.Grupo
import com.example.mensageiro.model.Tarefa

class InfoGrupoViewModel : ViewModel() {

    val grupo = MutableLiveData<Grupo>()
    val listaTarefas = MutableLiveData<List<Tarefa>>()

    fun retornarGrupo(idGrupo: String) {
        val task = GrupoDao.exibirGrupo(idGrupo)
        task.addOnSuccessListener {
            grupo.value = it.toObjects(Grupo::class.java).first()
        }
    }

    fun retornarTarefas(idGrupo: String) {
        val task = TarefaDao.listarTarefas(idGrupo)
        task.addSnapshotListener { snapshot, error ->
            if (snapshot != null) {
                listaTarefas.value = snapshot.toObjects(Tarefa::class.java)
            }
        }
    }

    fun excluirTarefa(idTarefa: String, idGrupo: String) {
        val task = TarefaDao.excluirTarefa(idTarefa)
        task.addOnSuccessListener {
            GrupoDao.deleteIdTarefaToGrupo(idTarefa, idGrupo)
        }
    }

    fun excluirGrupo(idGrupo: String) {
        GrupoDao.exibirGrupo(idGrupo).addOnSuccessListener {
            val grupo = it.toObjects(Grupo::class.java).first()
            if ((grupo.listaIdTarefas)!!.isNotEmpty()) {
                for (idTarefa in (grupo.listaIdTarefas!!)) {
                    TarefaDao.excluirTarefa(idTarefa)
                }
            }

            val idUsuario = AuthDao.getCurrentUser()!!.uid

            val task = GrupoDao.excluirGrupo(idGrupo)
            task.addOnSuccessListener {
                UsuarioDao.removeIdGrupotoUsuario(idUsuario, idGrupo)
            }
        }
    }
}