package com.example.ejercicio1

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(this)


        val lista = listOf(
            Producto("Laptop", 5, 2500.0, R.drawable.laptop),
            Producto("Mouse", 10, 50.0, R.drawable.mouse),
            Producto("Teclado", 15, 120.0, R.drawable.teclado),
            Producto("Monitor", 8, 800.0, R.drawable.monitor),
            Producto("Audífonos", 20, 150.0, R.drawable.audifonos)
        )
        guardarProductos(this, lista)

        val listaGuardada = obtenerProductos(this)
        recycler.adapter = ProductoAdapter(listaGuardada)
    }

    fun guardarProductos(context: Context, lista: List<Producto>) {
        val prefs = context.getSharedPreferences("productos", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val json = Gson().toJson(lista)
        editor.putString("lista_productos", json)
        editor.apply()
    }

    fun obtenerProductos(context: Context): List<Producto> {
        val prefs = context.getSharedPreferences("productos", Context.MODE_PRIVATE)
        val json = prefs.getString("lista_productos", null)

        return if (json != null) {
            val tipo = object : TypeToken<List<Producto>>() {}.type
            Gson().fromJson(json, tipo)
        } else {
            emptyList()
        }
    }
}
