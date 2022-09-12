package com.example.mensageiro.dao

import com.example.mensageiro.model.Grupo
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
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

        fun exibirGrupo(idGrupo: String): Task<QuerySnapshot> {
            return collection.whereEqualTo("id", idGrupo).get()
        }

        fun listarGrupos(listaIdGrupos: List<String>): Task<QuerySnapshot> {
            return collection.whereIn("id", listaIdGrupos).get()
        }
    }
}