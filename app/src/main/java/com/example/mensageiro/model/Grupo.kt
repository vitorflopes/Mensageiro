package com.example.mensageiro.model

data class Grupo (
    var id: String? = null,
    var idLider: String? = null,
    var nome: String? = null,
    var descricao: String? = null,
    var listaIdTarefas: MutableList<String>? = arrayListOf(),
    var listaIdParticipantes: MutableList<String>? = arrayListOf()
        )

data class Tarefa (
    val id: String? = null,
    val nome: String? = null,
    val descricao: String? = null,
    var idGrupo: String? = null
        )