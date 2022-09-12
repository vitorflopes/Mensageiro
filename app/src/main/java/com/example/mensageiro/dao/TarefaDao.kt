package com.example.mensageiro.dao

import com.example.mensageiro.model.Tarefa
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TarefaDao {

    companion object {
        private val collection = Firebase.firestore.collection("Tarefas")

        fun salvarTarefas(tarefa: Tarefa, idGrupo: String): Task<DocumentReference> {
            return collection.add(tarefa).addOnSuccessListener {
                val idTarefaCriada = it.id
                collection.document(idTarefaCriada).update("id", idTarefaCriada)
                collection.document(idTarefaCriada).update("idGrupo", idGrupo)
            }
        }

        fun listarTarefas(idGrupo: String): Query {
            return collection.whereEqualTo("idGrupo", idGrupo)
        }

        fun excluirTarefa(idTarefa: String): Task<Void> {
            return collection.document(idTarefa).delete()
        }
    }
}