package com.example.mensageiro.model

data class Usuario (
    val id: String? = null,
    val nome: String? = null,
    val email: String? = null,
    val senha: String? = null,
    val listaIdGrupos: MutableList<String>? = arrayListOf()
        )