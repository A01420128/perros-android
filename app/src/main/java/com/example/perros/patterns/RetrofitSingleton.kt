package com.example.perros.patterns

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton {
    val constante = "Estoy en un Singleton"
    public fun getRetroFit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

fun main(args: Array<String>) {
    println(RetrofitSingleton.toString())
    println(RetrofitSingleton.toString())
}