package com.example.mensageiro.model

data class Grupo (
    var id: String? = null,
    var idLider: String? = null,
    var nome: String? = null,
    var descricao: String? = null,
    var listaIdTarefas: MutableList<String>? = null,
    var listaIdParticipantes: MutableList<String>? = null
        )

data class Tarefa (
    val id: String? = null,
    val nome: String? = null,
    val descricao: String? = null,
    val concluida: Boolean? = false,
    val listaIdParticipantes: MutableList<String>? = null
        )