package com.example.recyclerview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView

class PeliculaAdapter (private val listaPeliculas: List<Pelicula>,val context: Context, val actividad:Activity) : RecyclerView.Adapter<PeliculaAdapter.miViewHolder>(){
    inner class miViewHolder(view: View, actividad:Activity) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        lateinit private var miItem: Pelicula
        var titulo: TextView
        var fecha: TextView
        var genero: TextView
        var posicion:Int = 0

        init {
            titulo = view.findViewById<View>(R.id.titulo) as TextView
            genero = view.findViewById<View>(R.id.genero) as TextView
            fecha = view.findViewById<View>(R.id.fecha) as TextView
            view.setOnClickListener(this)
        }

        fun setItem(miPelicula: Pelicula,position:Int) {
            miItem = miPelicula
            titulo.text = miPelicula.titulo
            genero.text = miPelicula.genero
            fecha.text = miPelicula.fecha
            this.posicion=position
        }

        override fun onClick(view: View) {
            //Toast.makeText((context as Activity),"has pulsado en ${miItem.titulo}. ${listaPeliculas.size}",Toast.LENGTH_SHORT).show()
            /*val intent = Intent(context ,MovieDetailActivity::class.java)
            intent.putExtra("posicion", miItem.position)
            (context as Activity).startActivity(intent)*/
            val bundle= bundleOf("posicion" to this.posicion)
            (actividad as MainActivity).navHost.navController.navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): miViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return miViewHolder(itemView, actividad)
    }

    override fun onBindViewHolder(holder: miViewHolder, position: Int) {
        val movie = listaPeliculas[position]
        holder.setItem(movie,position)
    }

    override fun getItemCount(): Int {
        return listaPeliculas.size
    }
}