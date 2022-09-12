package com.example.mensageiro.dao

import com.example.mensageiro.model.Grupo
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GrupoDao {

    companion object {
        private val collection = Firebase.firestore.collection("Grupos")

        fun salvarGrupo(grupo: Grupo): Task<DocumentReference> {
            return collection.add(grupo).addOnSuccessListener {
                val idGrupoCriado = it.id
                collection.document(idGrupoCriado).update("id", idGrupoCriado)
            }
        }

        fun excluirGrupo(idGrupo: String): Task<Void> {
            return collection.document(idGrupo).delete()
        }

        fun exibirGrupo(idGrupo: String): Task<QuerySnapshot> {
            return collection.whereEqualTo("id", idGrupo).get()
        }

        fun listarGrupos(listaIdGrupos: List<String>): Task<QuerySnapshot> {
            return collection.whereIn("id", listaIdGrupos).get()
        }

        fun addIdParticipanteToGrupo(idNovoParticipante: String, idGrupo: String): Task<QuerySnapshot> {
            lateinit var listaIdParticipantes: MutableList<String>

            val task = exibirGrupo(idGrupo).addOnSuccessListener {
                val grupo = it.toObjects(Grupo::class.java).first()
                listaIdParticipantes = grupo.listaIdParticipantes!!
                listaIdParticipantes.add(idNovoParticipante)
                collection.document(idGrupo).update("listaIdParticipantes", listaIdParticipantes)
            }

            return task
        }

        fun addIdTarefaToGrupo(idTarefa: String, idGrupo: String): Task<QuerySnapshot> {
            lateinit var listaIdTarefas: MutableList<String>

            val task = exibirGrupo(idGrupo).addOnSuccessListener {
                val grupo = it.toObjects(Grupo::class.java).first()
                if (grupo.listaIdTarefas != null) {
                    listaIdTarefas = grupo.listaIdTarefas!!
                    listaIdTarefas.add(idTarefa)
                }
                else {
                    listaIdTarefas = arrayListOf(idTarefa)
                }
                collection.document(idGrupo).update("listaIdTarefas", listaIdTarefas)
            }

            return task
        }

        fun deleteIdTarefaToGrupo(idTarefa: String, idGrupo: String): Task<QuerySnapshot> {
            lateinit var listaIdTarefas: MutableList<String>

            val task = exibirGrupo(idGrupo).addOnSuccessListener {
                val grupo = it.toObjects(Grupo::class.java).first()
                listaIdTarefas = grupo.listaIdTarefas!!
                listaIdTarefas.remove(idTarefa)

                collection.document(idGrupo).update("listaIdTarefas", listaIdTarefas)
            }

            return task
        }
    }
}