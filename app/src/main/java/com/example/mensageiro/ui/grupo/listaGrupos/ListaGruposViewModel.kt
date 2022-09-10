package com.example.mensageiro.ui.grupo.listaGrupos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mensageiro.dao.AuthDao
import com.example.mensageiro.dao.GrupoDao
import com.example.mensageiro.model.Grupo

class ListaGruposViewModel : ViewModel() {

    val listaGrupo = MutableLiveData<List<Grupo>>()

    fun retornarListaGrupo() {
        val task = GrupoDao.listarGrupos()

        val idUsuarioLogado = AuthDao.getCurrentUser()!!.uid


    }

}