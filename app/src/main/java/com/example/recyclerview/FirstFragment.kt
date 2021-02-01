package com.example.recyclerview

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var misPeliculas:MutableList<Pelicula> = mutableListOf()
    lateinit private var miAdaptador: PeliculaAdapter
    lateinit private var miRecyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_first, container, false)

        misPeliculas= (activity?.application as Aplicacion).listaPeliculas
        //InsertarDatos()
        this.miAdaptador= PeliculaAdapter(misPeliculas,activity as Context,activity as Activity)
        if(misPeliculas.size==0){
            rootView.findViewById<TextView>(R.id.frag1_texto).visibility=View.VISIBLE
            rootView.findViewById<RecyclerView>(R.id.recyclerView).visibility=View.GONE
        }
        else{
            rootView.findViewById<TextView>(R.id.frag1_texto).visibility=View.GONE
            rootView.findViewById<RecyclerView>(R.id.recyclerView).visibility=View.VISIBLE
            this.miRecyclerView=rootView.findViewById(R.id.recyclerView)
            miRecyclerView.layoutManager=LinearLayoutManager(activity)
            miRecyclerView.itemAnimator = DefaultItemAnimator()
            miRecyclerView.adapter=miAdaptador
            miAdaptador.notifyDataSetChanged()
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        miAdaptador.notifyDataSetChanged()
    }

    fun InsertarDatos(){
        misPeliculas.add(Pelicula("La joven del agua", "Fantasia","2002"))
        misPeliculas.add(Pelicula("Django desencadenado", "Western","2012"))
        misPeliculas.add(Pelicula("La profecía", "Terror","1984"))
        misPeliculas.add(Pelicula("Minority report", "Ciencia ficción","1998"))
    }
}