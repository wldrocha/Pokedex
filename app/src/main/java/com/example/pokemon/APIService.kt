package com.example.pokemon

import com.example.pokemon.Models.Pokemon
import com.example.pokemon.Models.PokemonsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getAllPokemons(@Url url: String):Response<PokemonsListResponse>

    @GET
    suspend fun getPokemon(@Url url: String):Response<Pokemon>
}