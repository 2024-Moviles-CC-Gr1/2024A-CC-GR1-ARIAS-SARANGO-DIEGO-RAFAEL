package com.example.a02_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class ECrudEntrenador : AppCompatActivity() {
    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_sqlite),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_entrenador)

        // BUSCAR ENTRENADOR
        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcion = findViewById<EditText>(R.id.input_descripcion)
            val entrenador = EBaseDeDatos.tablaEntrenador!!
                .consultarEntrenadorPorID(
                    id.text.toString().toInt()
                )
            if(entrenador == null){
                mostrarSnackbar("Usuario no encontrado")
                id.setText("")
                nombre.setText("")
                descripcion.setText("")
            }else{
                id.setText(entrenador.id.toString())
                nombre.setText(entrenador.nombre)
                descripcion.setText(entrenador.descripcion)
                mostrarSnackbar("Usuario encontrado")
            }

            //FUNCION PARA CREAR UN ENTRENADOR
            val botonBuscarBDD = findViewById<Button>(R.id.btn_crear_bdd)
            botonBuscarBDD.setOnClickListener {
                val nombre = findViewById<EditText>(R.id.input_nombre)
                val descripcion = findViewById<EditText>(R.id.input_descripcion)
                val respuesta = EBaseDeDatos.tablaEntrenador!!
                    .crearEntrenador(
                        nombre.text.toString(),
                        descripcion.text.toString()
                    )
                if(respuesta) mostrarSnackbar("Entr.Creado!")
            }

            // FUNCION PARA ACTUALIZAR UN ENTRENADOR
            val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
            botonActualizarBDD.setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id)
                val nombre = findViewById<EditText>(R.id.input_nombre)
                val descripcion = findViewById<EditText>(R.id.input_descripcion)
                val respuesta = EBaseDeDatos.tablaEntrenador!!
                    .actualizarEntrenadorFormulario(
                        nombre.text.toString(),
                        descripcion.text.toString(),
                        id.text.toString().toInt()
                    )
                if(respuesta) mostrarSnackbar("Entr. Actualizado!")
            }

            // FUNCION PARA ELIMINAR UN ENTRENADOR
            val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
            botonEliminarBDD.setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id)
                val respuesta = EBaseDeDatos.tablaEntrenador!!
                    .eliminarEntrenadorFormulario(
                        id.text.toString().toInt()
                    )
                if(respuesta) mostrarSnackbar("Entr. Eliminado!")
            }
        }
    }
}