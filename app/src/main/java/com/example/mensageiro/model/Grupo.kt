package com.example.mensageiro.model

data class Grupo (
    var idLider: String? = null,
    var nome: String? = null,
    var descricao: String? = null,
    var listaTarefas: MutableList<Tarefa>? = null,
    var listaParticipantes: MutableList<Usuario>? = null
        )

data class Tarefa (
    val id: String? = null,
    val nome: String? = null,
    val descricao: String? = null,
    val concluida: Boolean? = false,
    val listaParticipantes: MutableList<Usuario>? = null
        )