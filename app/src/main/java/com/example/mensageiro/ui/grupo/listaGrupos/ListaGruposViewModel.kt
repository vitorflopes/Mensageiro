package com.example.mensageiro.ui.grupo.listaGrupos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mensageiro.dao.AuthDao
import com.example.mensageiro.dao.GrupoDao
import com.example.mensageiro.dao.UsuarioDao
import com.example.mensageiro.model.Grupo
import com.example.mensageiro.model.Usuario

class ListaGruposViewModel : ViewModel() {

    val listaGrupo = MutableLiveData<List<Grupo>>()

    fun retornarListaGrupo() {
        val idUsuarioLogado = AuthDao.getCurrentUser()!!.uid

        val task = UsuarioDao.exibirUsuario(idUsuarioLogado)
        task.addOnSuccessListener {
            val usuario = it.toObjects(Usuario::class.java).first()

            val task = GrupoDao.listarGrupos(usuario.listaIdGrupos!!)
            task.addOnSuccessListener {
                listaGrupo.value = it.toObjects(Grupo::class.java)
            }
        }
    }
}