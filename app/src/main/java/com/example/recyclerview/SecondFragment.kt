package com.example.recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<EditText>(R.id.frag2_bInsertar).setOnClickListener{
            if (validar()){
                //(activity?.application as Aplicacion).listaPeliculas.add(Pelicula(titulo, genero, fecha))
                //Toast.makeText(activity,"Pelicula creada",Toast.LENGTH_SHORT)
            }
        }

        view.findViewById<EditText>(R.id.frag2_bModificar).setOnClickListener{
            if (validar()){
                //(activity?.application as Aplicacion).listaPeliculas.add(Pelicula(titulo, genero, fecha))
                //Toast.makeText(activity,"Pelicula modificada",Toast.LENGTH_SHORT)
            }
        }

        view.findViewById<EditText>(R.id.frag2_bBorrar).setOnClickListener{
            //(activity?.application as Aplicacion).listaPeliculas.add(Pelicula(titulo, genero, fecha))
            //Toast.makeText(activity,"Pelicula borrada",Toast.LENGTH_SHORT)
        }

    }

    fun validar():Boolean{
        var errores:String=""
        val titulo:EditText=view.findViewById<EditText>(R.id.frag2_etTitulo)
        val genero:EditText=view.findViewById<EditText>(R.id.frag2_etGenero)
        val fecha:EditText=view.findViewById<EditText>(R.id.frag2_etFecha)
        if(titulo.text.isEmpty()) errores+="Tienes que insertar un título"
        if(genero.text.isEmpty()) errores+="Tienes que insertar un genero"
        if(fecha.text.isEmpty()) errores+="Tienes que insertar un fecha"
        if(errores!="") Toast.makeText(activity,"$errores",Toast.LENGTH_SHORT);return false
        return true
    }
}