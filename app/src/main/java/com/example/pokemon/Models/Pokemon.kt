package com.example.pokemon.Models

data class Pokemon (val id: Int , val name:String, val order:Int, val sprites: Sprites)

data class Sprites(val back_default:String, val front_default:String)