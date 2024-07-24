package com.example.a03_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class ECrudPartes : AppCompatActivity() {

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_partes),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_partes)

        // BUSCAR PARTE
        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_parte)
            val idAuto = findViewById<EditText>(R.id.input_id_auto)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val numeroSerie = findViewById<EditText>(R.id.input_numero_serie)
            val fechaFabricacion = findViewById<EditText>(R.id.input_fecha_fabricacion)
            val precio = findViewById<EditText>(R.id.input_precio_parte)
            val parte = EBaseDeDatosPartes.tablaPartes!!
                .consultarPartePorID(
                    id.text.toString().toInt()
                )
            if (parte == null) {
                mostrarSnackbar("Parte no encontrada")
                id.setText("")
                idAuto.setText("")
                nombre.setText("")
                numeroSerie.setText("")
                fechaFabricacion.setText("")
                precio.setText("")
            } else {
                id.setText(parte.id_parte.toString())
                idAuto.setText(parte.id_auto)
                nombre.setText(parte.nombre)
                numeroSerie.setText(parte.numero_serie)
                fechaFabricacion.setText(parte.fecha_fabricacion)
                precio.setText(parte.precio.toString())
                mostrarSnackbar("Parte encontrada")
            }
        }

        // FUNCION PARA CREAR UNA PARTE
        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd)
        botonCrearBDD.setOnClickListener {
            val idAuto = findViewById<EditText>(R.id.input_id_auto)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val numeroSerie = findViewById<EditText>(R.id.input_numero_serie)
            val fechaFabricacion = findViewById<EditText>(R.id.input_fecha_fabricacion)
            val precio = findViewById<EditText>(R.id.input_precio_parte)
            val respuesta = EBaseDeDatosPartes.tablaPartes!!
                .crearParte(
                    idAuto.text.toString().toInt(),
                    nombre.text.toString(),
                    numeroSerie.text.toString(),
                    fechaFabricacion.text.toString(),
                    precio.text.toString().toDouble()
                )
            if (respuesta) mostrarSnackbar("Parte Creada!")
        }

        // FUNCION PARA ACTUALIZAR UNA PARTE
        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_parte)
            val idAuto = findViewById<EditText>(R.id.input_id_auto)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val numeroSerie = findViewById<EditText>(R.id.input_numero_serie)
            val fechaFabricacion = findViewById<EditText>(R.id.input_fecha_fabricacion)
            val precio = findViewById<EditText>(R.id.input_precio_parte)
            val respuesta = EBaseDeDatosPartes.tablaPartes!!
                .actualizarParteFormulario(
                    id.text.toString().toInt(),
                    idAuto.text.toString().toInt(),
                    nombre.text.toString(),
                    numeroSerie.text.toString(),
                    fechaFabricacion.text.toString(),
                    precio.text.toString().toDouble()
                )
            if (respuesta) mostrarSnackbar("Parte Actualizada!")
        }

        // FUNCION PARA ELIMINAR UNA PARTE
        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
        botonEliminarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_parte)
            val respuesta = EBaseDeDatosPartes.tablaPartes!!
                .eliminarParteFormulario(
                    id.text.toString().toInt()
                )
            if (respuesta) mostrarSnackbar("Parte Eliminada!")
        }
    }
}
