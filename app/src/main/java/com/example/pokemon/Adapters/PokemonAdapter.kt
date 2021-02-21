package com.example.pokemon.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.Models.Pokemon
import com.example.pokemon.R
import com.example.pokemon.databinding.PokemonItemBinding
import com.squareup.picasso.Picasso


class PokemonAdapter(val context: Context, val pokemon: List<Pokemon>, val itemClickLister: OnPokemonClickListener) : RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    interface OnPokemonClickListener{
        fun onItemClickListener(name: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val layoutInflater = LayoutInflater.from(context)
        return PokemonHolder(layoutInflater.inflate(R.layout.pokemon_item, parent, false))
    }

    //Shorthand de functions
    override fun getItemCount(): Int = pokemon.size

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) = holder.render(pokemon[position], position)

    //Inner: class dead when parent dead
    inner class PokemonHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val binding = PokemonItemBinding.bind(view)

        fun render(pokemon: Pokemon, position: Int) {
            Picasso.get().load(pokemon.sprites.front_default).into(binding.imagePokemon)
            //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(binding.imagePokemon);
            binding.idPokemon.text = pokemon.id.toString()
            binding.pokemonText.text = pokemon.name

         //   itemView.setOnClickListener { itemClickLister.onItemClickListener(pokemon.name) }

        }
    }
}

