package com.example.pokemon.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.APIService
import com.example.pokemon.Adapters.PokemonAdapter
import com.example.pokemon.Models.Pokemon
import com.example.pokemon.Models.PokemonsResponse
import com.example.pokemon.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), PokemonAdapter.OnPokemonClickListener {

    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter: PokemonAdapter
    private val pokemons = mutableListOf<Pokemon>()

    /*val pokemons = listOf(
             Pokemon(1, "bulbasaur", 1, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
             Pokemon(2, "ivysaur", 2, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"),
             Pokemon(3, "venusaur", 3, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"),
             Pokemon(4, "charmander", 4, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"),
             Pokemon(5, "charmeleon", 5, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png"),
             Pokemon(6, "charizard", 6, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png")
     )*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        this.adapter = PokemonAdapter(this, pokemons, this)
        binding.recyclerViewPokemon.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPokemon.adapter = adapter
        searchBy()
    }

    override fun onItemClickListener(name: String) {
        Toast.makeText(this,"i'm click", Toast.LENGTH_SHORT)
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun searchBy(){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<PokemonsResponse> = getRetrofit().create(APIService::class.java).getAllPokemons("pokemon/")
            val responseCall: PokemonsResponse? = call.body()
            runOnUiThread {
                val pokemonList: List<Pokemon>? = responseCall?.pokemons ?: emptyList()

                if(call.isSuccessful && pokemonList != null){
                    pokemons.addAll(pokemonList)
                    adapter.notifyDataSetChanged()
                }else{
                    showError()
                }
            }
        }
    }

    private fun showError(){
        Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}