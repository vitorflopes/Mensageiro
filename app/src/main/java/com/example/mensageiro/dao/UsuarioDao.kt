package com.example.mensageiro.dao

import com.example.mensageiro.model.Usuario
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UsuarioDao {

    companion object {
        private val collection = Firebase.firestore.collection("Usuarios")

        fun salvarUsuario(idUsuario: String, usuario: Usuario) {
            collection.document(idUsuario).set(usuario)
        }

        fun exibirUsuario(idUsuario: String): Task<QuerySnapshot> {
            return collection.whereEqualTo("id", idUsuario).get()
        }

        fun exibirUsuarioByEmail(emailUsuario: String): Task<QuerySnapshot> {
            return collection.whereEqualTo("email", emailUsuario).get()
        }

        fun addIdGrupotoUsuario(idUsuario: String, idGrupo: String): Task<QuerySnapshot> {
            lateinit var listaIdgrupos: MutableList<String>

            val task = exibirUsuario(idUsuario).addOnSuccessListener {
                val usuario = it.toObjects(Usuario::class.java).first()
                listaIdgrupos = usuario.listaIdGrupos!!
                listaIdgrupos.add(idGrupo)
                collection.document(idUsuario).update("listaIdGrupos", listaIdgrupos)
            }

            return task
        }
    }
}