package com.example.a03_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class ECrudAutos : AppCompatActivity() {

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_autos),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_autos)

        // BUSCAR AUTO
        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val marca = findViewById<EditText>(R.id.input_marca)
            val anio = findViewById<EditText>(R.id.input_anio)
            val precio = findViewById<EditText>(R.id.input_precio_auto)
            val electrico = findViewById<EditText>(R.id.input_electrico)
            val auto = EBaseDeDatosAutos.tablaAuto!!
                .consultarAutoPorID(
                    id.text.toString().toInt()
                )
            if (auto == null) {
                mostrarSnackbar("Auto no encontrado")
                id.setText("")
                marca.setText("")
                anio.setText("")
                precio.setText("")
                electrico.setText("")
            } else {
                id.setText(auto.id_auto.toString())
                marca.setText(auto.marca)
                anio.setText(auto.anio.toString())
                precio.setText(auto.precio.toString())
                electrico.setText(auto.electrico)
                mostrarSnackbar("Auto encontrado")
            }
        }

        // FUNCION PARA CREAR UN AUTO
        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd)
        botonCrearBDD.setOnClickListener {
            val marca = findViewById<EditText>(R.id.input_marca)
            val anio = findViewById<EditText>(R.id.input_anio)
            val precio = findViewById<EditText>(R.id.input_precio_auto)
            val electrico = findViewById<EditText>(R.id.input_electrico)
            val respuesta = EBaseDeDatosAutos.tablaAuto!!
                .crearAuto(
                    marca.text.toString(),
                    anio.text.toString().toInt(),
                    precio.text.toString().toDouble(),
                    electrico.text.toString()
                )
            if (respuesta) mostrarSnackbar("Auto Creado!")
        }

        // FUNCION PARA ACTUALIZAR UN AUTO
        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val marca = findViewById<EditText>(R.id.input_marca)
            val anio = findViewById<EditText>(R.id.input_anio)
            val precio = findViewById<EditText>(R.id.input_precio_auto)
            val electrico = findViewById<EditText>(R.id.input_electrico)
            val respuesta = EBaseDeDatosAutos.tablaAuto!!
                .actualizarAutoFormulario(
                    id.text.toString().toInt(),
                    marca.text.toString(),
                    anio.text.toString().toInt(),
                    precio.text.toString().toDouble(),
                    electrico.text.toString()
                )
            if (respuesta) mostrarSnackbar("Auto Actualizado!")
        }

        // FUNCION PARA ELIMINAR UN AUTO
        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
        botonEliminarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val respuesta = EBaseDeDatosAutos.tablaAuto!!
                .eliminarAutoFormulario(
                    id.text.toString().toInt()
                )
            if (respuesta) mostrarSnackbar("Auto Eliminado!")
        }
    }
}
