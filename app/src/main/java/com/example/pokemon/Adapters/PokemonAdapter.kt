package com.example.pokemon.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.Models.Pokemon
import com.example.pokemon.R
import com.example.pokemon.databinding.PokemonItemBinding
import com.squareup.picasso.Picasso


class PokemonAdapter(val pokemon: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonHolder(layoutInflater.inflate(R.layout.pokemon_item, parent, false))
    }

    //Shorthand de funciones
    override fun getItemCount(): Int = pokemon.size

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) = holder.render(pokemon[position])

    class PokemonHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val binding = PokemonItemBinding.bind(view)

        fun render(pokemon: Pokemon) {
            Picasso.get().load(pokemon.img).into(binding.imagePokemon)
            //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(binding.imagePokemon);
            binding.idPokemon.text = pokemon.id.toString()
            binding.pokemonText.text = pokemon.name.toString()
            view.setOnClickListener()
        }
    }
}

