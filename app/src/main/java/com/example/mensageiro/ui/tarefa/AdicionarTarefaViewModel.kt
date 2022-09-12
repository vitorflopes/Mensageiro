package com.example.mensageiro.ui.tarefa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mensageiro.dao.TarefaDao
import com.example.mensageiro.model.Tarefa

class AdicionarTarefaViewModel : ViewModel() {

    val status = MutableLiveData<Boolean>()

    init {
        status.value = false
    }

    fun adicionarTarefa(idGrupo: String, tarefa: Tarefa) {
        TarefaDao.salvarTarefas(tarefa, idGrupo).addOnSuccessListener {
            status.value = true
        }
    }
}