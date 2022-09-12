package com.example.mensageiro

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class EditarGrupoFragment : Fragment() {

    companion object {
        fun newInstance() = EditarGrupoFragment()
    }

    private lateinit var viewModel: EditarGrupoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_editar_grupo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditarGrupoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}