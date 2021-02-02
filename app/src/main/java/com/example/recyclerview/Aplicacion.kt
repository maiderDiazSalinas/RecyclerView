package com.example.recyclerview

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.*

class Aplicacion : Application() {

    public val listaPeliculas:MutableList<Pelicula> =mutableListOf()

    override fun onCreate() {
        super.onCreate()
        //InsertarDatos()
        //SacarDatos()
        SacarDatosExterno()
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
                        openFileOutput("peliculas.txt", MODE_PRIVATE)
                    )
                )

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
    fun GuardaDatosExterno() {
        try {
            val ruta_sd = getExternalFilesDir("/mnt/sd_card")

            val f = File(ruta_sd?.getAbsolutePath(), "prueba_sd.txt")

            val fout = BufferedWriter(OutputStreamWriter(
                FileOutputStream(f)))

            for (pelicula in listaPeliculas){
                fout.write(pelicula.titulo)
                fout.newLine()
                fout.write(pelicula.genero)
                fout.newLine()
                fout.write(pelicula.fecha)
                fout.newLine()
            }

            fout.close()
            Toast.makeText(this, "Fichero creado externo", Toast.LENGTH_LONG).show()
        } catch (ex: Exception) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD")
        }

    }

    fun SacarDatosExterno() {
        try {
            val ruta_sd = getExternalFilesDir("/mnt/sd_card")

            val f = File(ruta_sd?.getAbsolutePath(), "prueba_sd.txt")

            val fin = BufferedReader(
                InputStreamReader(
                    FileInputStream(f)
                ))

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
            Log.e("Ficheros", "Error al leer fichero desde tarjeta SD")
        }

    }


}