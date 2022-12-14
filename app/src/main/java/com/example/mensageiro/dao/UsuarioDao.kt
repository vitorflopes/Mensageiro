package com.example.mensageiro.dao

import com.example.mensageiro.model.Usuario
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class UsuarioDao {

    companion object {
        private val collection = Firebase.firestore.collection("Usuarios")

        fun salvarUsuario(idUsuario: String, usuario: Usuario): Task<Void> {
            return collection.document(idUsuario).set(usuario)
        }

        fun retornaUsuariosDoGrupo(idGrupo: String): Task<QuerySnapshot> {
            return collection.whereArrayContains("idGrupos", idGrupo).get()
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

        fun removeIdGrupotoUsuario(idUsuario: String, idGrupo: String): Task<QuerySnapshot> {
            lateinit var listaIdGrupos: MutableList<String>

            val task = exibirUsuario(idUsuario).addOnSuccessListener {
                val usuario = it.toObjects(Usuario::class.java).first()
                listaIdGrupos = usuario.listaIdGrupos!!
                listaIdGrupos.remove(idGrupo)

                collection.document(idUsuario).update("listaIdGrupos", listaIdGrupos)
            }

            return task
        }

        fun salvaUserFacebook(idUsuario: String, nomeUsuario: String, emailUsuario: String): Task<QuerySnapshot> {
            return  exibirUsuario(idUsuario).addOnSuccessListener {
                if (it.toObjects(Usuario::class.java).size == 0) {
                    val usuario = Usuario(idUsuario, nomeUsuario, emailUsuario)
                    salvarUsuario(idUsuario, usuario)
                }
            }
        }
    }
}