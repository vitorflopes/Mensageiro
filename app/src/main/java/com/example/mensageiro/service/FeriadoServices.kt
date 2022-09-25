package com.example.mensageiro.service

import com.example.mensageiro.model.Feriado
import retrofit2.http.GET

interface FeriadoServices {

    @GET("2022")
    suspend fun getFeriados() : List<Feriado>

}