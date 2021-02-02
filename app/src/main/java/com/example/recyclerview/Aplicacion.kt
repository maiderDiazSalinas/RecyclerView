package com.example.recyclerview

import android.app.Application
import android.util.Log
import android.widget.Toast
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class Aplicacion : Application() {

    public val listaPeliculas:MutableList<Pelicula> =mutableListOf()

    override fun onCreate() {
        super.onCreate()
        //InsertarDatos()
        SacarDatos()
    }

    /*fun InsertarDatos(){
        listaPeliculas.add(Pelicula("La joven del agua", "Fantasia","2002"))
        listaPeliculas.add(Pelicula("Django desencadenado", "Western","2012"))
        listaPeliculas.add(Pelicula("La profecía", "Terror","1984"))
        listaPeliculas.add(Pelicula("Minority report", "Ciencia ficción","1998"))
    }*/

    fun GuardarDatos(){
        try {
            val fout = BufferedWriter(
                OutputStreamWriter(
                    openFileOutput("peliculas.txt", MODE_PRIVATE)))

            for (pelicula in listaPeliculas){
                fout.write(pelicula.titulo)
                fout.newLine()
                fout.write(pelicula.genero)
                fout.newLine()
                fout.write(pelicula.fecha)
                fout.newLine()
            }
            fout.close()
            Toast.makeText(this, "Fichero creado internamente", Toast.LENGTH_LONG).show()
        } catch (ex: Exception) {
            Toast.makeText(this, "Error al crear fichero internamente", Toast.LENGTH_LONG).show()
            Log.e("Ficheros", "Error al escribir fichero a memoria interna")
        }
    }

    fun SacarDatos(){
        try {
            val fin = BufferedReader(
                InputStreamReader(
                    openFileInput("peliculas.txt"))
            )
            var titulo:String=""
            var genero:String=""
            var fecha:String=""
            var linea:String?=null
            var num:Int=0
            do{
                if(linea!=null) {
                    num++
                    if (num==1) titulo=linea
                    else if(num==2) genero=linea
                    else if(num==3) {
                        fecha=linea
                        num=0
                    }
                    if(num==0) listaPeliculas.add(Pelicula(titulo,genero,fecha))
                }
                linea=fin.readLine()
                //Toast.makeText(this, linea, Toast.LENGTH_LONG).show()
            }while(linea!=null)
            fin.close()
        } catch (ex: Exception) {
            Toast.makeText(this, "Error al leer el fichero internamente", Toast.LENGTH_LONG).show()
            Log.e("Ficheros", "Error al leer fichero desde memoria interna")
        }
    }
}