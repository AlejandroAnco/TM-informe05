package com.example.ejercicio1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(private val lista: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView = view.findViewById(R.id.ivProducto)
        val nombre: TextView = view.findViewById(R.id.tvNombre)
        val cantidad: TextView = view.findViewById(R.id.tvCantidad)
        val precio: TextView = view.findViewById(R.id.tvPrecio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = lista[position]
        holder.imagen.setImageResource(producto.imagen)
        holder.nombre.text = producto.nombre
        holder.cantidad.text = "Cantidad: ${producto.cantidad}"
        holder.precio.text = "Precio: S/ ${producto.precio}"
    }

    override fun getItemCount() = lista.size
}
