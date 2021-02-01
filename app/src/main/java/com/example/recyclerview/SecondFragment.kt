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

    lateinit var titulo:EditText
    lateinit var genero:EditText
    lateinit var fecha:EditText

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titulo=view.findViewById(R.id.frag2_etTitulo)
        genero=view.findViewById(R.id.frag2_etGenero)
        fecha=view.findViewById(R.id.frag2_etFecha)

        val posicion: Int? = arguments?.getInt("posicion")
        if(posicion!=null){
            titulo.setText((activity?.application as Aplicacion).listaPeliculas[posicion].titulo)
            genero.setText((activity?.application as Aplicacion).listaPeliculas[posicion].genero)
            fecha.setText((activity?.application as Aplicacion).listaPeliculas[posicion].fecha)
            view.findViewById<Button>(R.id.frag2_bInsertar).setEnabled(false)
        }
        else{
            view.findViewById<Button>(R.id.frag2_bModificar).setEnabled(false)
            view.findViewById<Button>(R.id.frag2_bBorrar).setEnabled(false)
        }

        view.findViewById<Button>(R.id.frag2_bInsertar).setOnClickListener{
            if (validar(view)){
                (activity?.application as Aplicacion).listaPeliculas.add(Pelicula(titulo.text.toString(), genero.text.toString(), fecha.text.toString()))
                Toast.makeText(activity,"Pelicula creada",Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<Button>(R.id.frag2_bModificar).setOnClickListener{
            if (validar(view)){
                val posicion: Int? = arguments?.getInt("posicion")
                if (posicion!=null) {
                    (activity?.application as Aplicacion).listaPeliculas[posicion].titulo = titulo.text.toString()
                    (activity?.application as Aplicacion).listaPeliculas[posicion].genero = genero.text.toString()
                    (activity?.application as Aplicacion).listaPeliculas[posicion].fecha = fecha.text.toString()
                    Toast.makeText(activity,"Pelicula modificada",Toast.LENGTH_SHORT).show()
                }
            }
        }

        view.findViewById<Button>(R.id.frag2_bBorrar).setOnClickListener{
            val posicion: Int? = arguments?.getInt("posicion")
            if (posicion!=null) {
                (activity?.application as Aplicacion).listaPeliculas.removeAt(posicion)
                Toast.makeText(activity,"Pelicula eliminada",Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun validar(view:View):Boolean{
        var errores=""
        if(titulo.text.isEmpty()) errores+="Tienes que insertar un título"
        if(genero.text.isEmpty()) errores+="Tienes que insertar un genero"
        if(fecha.text.isEmpty()) errores+="Tienes que insertar un fecha"
        if(errores!="") {
            Toast.makeText(activity,"$errores",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}