package com.example.recyclerview.ui.login

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.recyclerview.MainActivity

import com.example.recyclerview.R

class LoginFragment : Fragment() {

    var sharedPref: SharedPreferences? = null
    lateinit var usuario:EditText
    lateinit var clave:EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usuario = view.findViewById<EditText>(R.id.login_username)
        clave = view.findViewById<EditText>(R.id.login_password)
        val loginButton = view.findViewById<Button>(R.id.login_bLogin)
        val singinButton= view.findViewById<Button>(R.id.login_bSingin)
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.loading)

        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        loginButton.setOnClickListener {
            if (Validar()){
                loadingProgressBar.visibility = View.VISIBLE
                Comprobar()
            }
        }

        singinButton.setOnClickListener {
            if(Validar()){
                val editor = sharedPref?.edit()
                editor?.putString("user",usuario.text.toString())
                editor?.putString("password",clave.text.toString())
                editor?.apply()
                Toast.makeText(activity as Context, "usuario insertado", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun Comprobar(){
        val usuario =  sharedPref?.getString("user","valor predefinido")
        val password =  sharedPref?.getString("password","valor predefinido")
        if(this.usuario.text.toString()==usuario && this.clave.text.toString()==password){
            (activity as MainActivity).navHost.navController.navigate(R.id.action_loginFragment_to_FirstFragment)
        }
        else{
            Toast.makeText(activity as Context, "usuario y contraseñas no validas", Toast.LENGTH_SHORT).show()
        }
    }

    fun Validar():Boolean{
        if(usuario.text.toString().isNotEmpty() && clave.text.toString().isNotEmpty()) return true
        else{
            Toast.makeText(activity as Context, "Tienes que insertar usuario y contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
    }


}