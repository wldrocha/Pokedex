package com.example.pokemon.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.Adapters.PokemonAdapter
import com.example.pokemon.Models.Pokemon
import com.example.pokemon.R
import com.example.pokemon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PokemonAdapter.OnPokemonClickListener {

     val pokemons = listOf(
             Pokemon(1, "bulbasaur", 1, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
             Pokemon(2, "ivysaur", 2, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"),
             Pokemon(3, "venusaur", 3, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"),
             Pokemon(4, "charmander", 4, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"),
             Pokemon(5, "charmeleon", 5, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png"),
             Pokemon(6, "charizard", 6, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png")
     )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initRecycler()
        Toast.makeText(this,"hi", Toast.LENGTH_SHORT)

        binding.recyclerViewPokemon.layoutManager = LinearLayoutManager(this)
        val pokemonAdapter = PokemonAdapter(this, pokemons, this)
        binding.recyclerViewPokemon.adapter = pokemonAdapter

    }

    override fun onItemClickListener(name: String) {
        Toast.makeText(this,"i'm click", Toast.LENGTH_SHORT)
    }
}