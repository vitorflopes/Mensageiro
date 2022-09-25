package com.example.mensageiro.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFit {
    companion object {
        fun retrofit() : Retrofit = Retrofit.Builder().baseUrl("https://brasilapi.com.br/api/feriados/v1/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        fun feriadoServices() = retrofit().create(FeriadoServices::class.java)
    }
}