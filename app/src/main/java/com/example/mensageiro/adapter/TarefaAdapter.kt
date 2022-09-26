package com.example.mensageiro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mensageiro.R
import com.example.mensageiro.model.Tarefa

class TarefaAdapter (
    private val listaTarefas: List<Tarefa>,
    val tarefaSelecionadaExcluir: (Tarefa) -> Unit)
    : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tarefa_adapter, parent, false)
        return TarefaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listaTarefas[position]

        holder.tvNomeTarefaAdapter.text = tarefa.nome
        holder.tvDescricaoTarefaAdapter.text = tarefa.descricao

        holder.btnExcluirTarefaAdapter.setOnClickListener {
            tarefaSelecionadaExcluir(tarefa)
        }
    }

    override fun getItemCount() = listaTarefas.size

    class TarefaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNomeTarefaAdapter: TextView = itemView.findViewById(R.id.tvNomeTarefaAdapter)
        val tvDescricaoTarefaAdapter: TextView = itemView.findViewById(R.id.tvDescricaoTarefaAdapter)
        val btnExcluirTarefaAdapter: ImageView = itemView.findViewById(R.id.btnDeletarTarefaAdapter)
    }
}