package com.example.pokemon.Models

import com.google.gson.annotations.SerializedName

data class PokemonsListResponse(
    var count: Int,
    var next: String,
    var previous: Any,
    @SerializedName("results") var pokemons: List<Pokemon>)


/*

"count": 1118,
  "next": "https://pokeapi.co/api/v2/pokemon/?offset=20&limit=20",
  "previous": null,
  "results":
 */
