package com.example.mensageiro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mensageiro.R
import com.example.mensageiro.model.Grupo

class GrupoAdapter(
    private val listaGrupos: List<Grupo>,
    val grupoSelecionado: (Grupo) -> Unit)
    : RecyclerView.Adapter<GrupoAdapter.GrupoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrupoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.grupo_adapter, parent, false)
        return GrupoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GrupoViewHolder, position: Int) {
        val grupo = listaGrupos[position]

        holder.tvNomeGrupoListGrupo.text = grupo.nome
        holder.tvDescricaoGrupoListGrupo.text = grupo.descricao

        holder.itemView.setOnClickListener {
            grupoSelecionado(grupo)
        }
    }

    override fun getItemCount() = listaGrupos.size

    class GrupoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNomeGrupoListGrupo: TextView = itemView.findViewById(R.id.tvNomeGrupoListGrupo)
        val tvDescricaoGrupoListGrupo: TextView = itemView.findViewById(R.id.tvDescricaoGrupoListGrupo)
    }
}