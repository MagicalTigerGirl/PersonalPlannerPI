package com.example.personalplanner.core

import co.infinum.retromock.Retromock
import com.example.personalplanner.data.network.PersonalPlannerService
import io.ktor.client.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonalPlannerClient(retrofit: Retrofit, retromock: Retromock) {

    val service = retrofit.create(PersonalPlannerService::class.java)

    val serviceMock = retromock.create(PersonalPlannerService::class.java)
}