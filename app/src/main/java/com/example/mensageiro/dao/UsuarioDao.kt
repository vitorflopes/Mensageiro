package com.example.mensageiro.dao

import com.example.mensageiro.model.Usuario
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UsuarioDao {

    companion object {
        private val collection = Firebase.firestore.collection("Usuarios")

        fun salvarUsuario(idUsuario: String, usuario: Usuario) {
            collection.document(idUsuario).set(usuario)
        }

        fun exibirUsuario(idUsuario: String): Query {
            return collection.whereEqualTo("id", idUsuario)
        }
    }

}