package com.example.pokemon.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.APIService
import com.example.pokemon.Adapters.PokemonAdapter
import com.example.pokemon.Models.Pokemon
import com.example.pokemon.Models.PokemonsListResponse
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
    private var pokemonsPage: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){

        binding.recyclerViewPokemon.layoutManager = LinearLayoutManager(this)
        this.adapter = PokemonAdapter(this, pokemons, this)
        binding.recyclerViewPokemon.adapter = adapter
        getAllPokemons(pokemonsPage)
        setRecyclerViewScrollListener()
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

    private fun getAllPokemons(limit: Int){
        val limitX = limit *  10
        val offset =  if(limit == 2)  0 else limitX
        CoroutineScope(Dispatchers.IO).launch {
            val callPokemonsList: Response<PokemonsListResponse> = getRetrofit().create(APIService::class.java).getAllPokemons("pokemon/?offset=$offset&limit=$limitX")
            val listResponseCall: PokemonsListResponse? = callPokemonsList.body()

            val pokemonList: List<Pokemon>? = listResponseCall?.pokemons ?: emptyList()
            if(pokemonList != null){
                for (pokemon in pokemonList){
                    val callPokemon: Response<Pokemon> = getRetrofit().create(APIService::class.java).getPokemon("pokemon/${pokemon.name}")
                    val responseCall: Pokemon? = callPokemon.body()
                    if (responseCall != null) {
                        pokemons.add(responseCall)
                    }
                }
            }
           runOnUiThread {
                if(callPokemonsList.isSuccessful){
                    adapter.notifyDataSetChanged()
                    println("pokemonsCount ${pokemons.size}")
                }else{
                    showError()
                }
            }
        }
    }

    private fun setRecyclerViewScrollListener(){

        binding.recyclerViewPokemon.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if(!binding.recyclerViewPokemon.canScrollVertically(1)){
                    pokemonsPage += 1
                    getAllPokemons(pokemonsPage)
                }
                /*if(dy > 0){
                    val visibleCount = binding.recyclerViewPokemon.childCount
                    val pastVisibleItem = binding.recyclerViewPokemon.canScrollVertically(1)
                }*/
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun showError(){
        Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}