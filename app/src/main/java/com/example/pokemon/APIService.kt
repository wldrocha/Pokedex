package com.example.pokemon

import com.example.pokemon.Models.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getAllPokemons(@Url url: String):Response<PokemonsResponse>
}