package com.example.mensageiro.dao

import com.example.mensageiro.model.Grupo
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GrupoDao {

    companion object {
        private val collection = Firebase.firestore.collection("Grupos")

        fun salvarGrupo(grupo: Grupo) {
            collection.document(grupo.nome!!).set(grupo)
        }

        fun listarGrupos(): Task<QuerySnapshot> {
            return collection.get()
        }
    }
}