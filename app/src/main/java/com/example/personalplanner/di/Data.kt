package com.example.personalplanner.di

import co.infinum.retromock.Retromock
import com.example.personalplanner.core.PersonalPlannerClient
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.listafacturas.domain.GetComidasUseCase
import com.example.personalplanner.domain.GetGastosUseCase
import com.example.listafacturas.viewmodel.ComidaViewModel
import com.example.personalplanner.viewmodel.GastosViewModel
import com.example.personalplanner.data.repository.ComidaRepository
import com.example.personalplanner.data.repository.GastosRepository
import org.koin.androidx.viewmodel.dsl.viewModelOf

val dataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:80/pi/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        Retromock.Builder()
            .retrofit(get())
            .build()
    }

    factoryOf(::PersonalPlannerClient)
    factoryOf(::GetComidasUseCase)
    factoryOf(::ComidaRepository)

    factoryOf(::GetGastosUseCase)
    factoryOf(::GastosRepository)
}

val viewModelModule = module {
    viewModelOf(::ComidaViewModel)
    viewModelOf(::GastosViewModel)
}