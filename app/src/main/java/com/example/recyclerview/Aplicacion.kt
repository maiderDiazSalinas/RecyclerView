package com.example.recyclerview

import android.app.Application

class Aplicacion : Application() {

    public val listaPeliculas:MutableList<Pelicula> =mutableListOf()

    override fun onCreate() {
        super.onCreate()
        InsertarDatos()
    }

    fun InsertarDatos(){
        listaPeliculas.add(Pelicula("La joven del agua", "Fantasia","2002"))
        listaPeliculas.add(Pelicula("Django desencadenado", "Western","2012"))
        listaPeliculas.add(Pelicula("La profecía", "Terror","1984"))
        listaPeliculas.add(Pelicula("Minority report", "Ciencia ficción","1998"))
    }
}