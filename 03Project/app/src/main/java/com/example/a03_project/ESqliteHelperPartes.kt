package com.example.a03_project

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperPartes(
    contexto: Context? // this
) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Crear una tabla
        val scriptSQLCrearTablaParte =
            """
                CREATE TABLE PARTE(
                    id_parte INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_auto INTEGER,
                    nombre VARCHAR(50),
                    numero_serie VARCHAR(50),
                    fecha_fabricacion VARCHAR(50),
                    precio DOUBLE
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaParte)
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun crearParte(
        id_auto: Int,
        nombre: String,
        numero_serie: String,
        fecha_fabricacion: String,
        precio: Double
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id_auto", id_auto)
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("numero_serie", numero_serie)
        valoresAGuardar.put("fecha_fabricacion", fecha_fabricacion)
        valoresAGuardar.put("precio", precio)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "PARTE", // nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return resultadoGuardar != -1L
    }

    fun eliminarParteFormulario(id_parte: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id_parte.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "PARTE",
                "id_parte=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return resultadoEliminacion != -1
    }

    fun actualizarParteFormulario(
        id_parte: Int,
        id_auto: Int,
        nombre: String,
        numero_serie: String,
        fecha_fabricacion: String,
        precio: Double
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("id_auto", id_auto)
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("numero_serie", numero_serie)
        valoresAActualizar.put("fecha_fabricacion", fecha_fabricacion)
        valoresAActualizar.put("precio", precio)
        val parametrosConsultaActualizar = arrayOf(id_parte.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "PARTE",
                valoresAActualizar,
                "id_parte=?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun consultarPartePorID(id_parte: Int): BPartes? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM PARTE WHERE id_parte = ?
        """.trimIndent()
        val arregloParametrosConsultaLectura = arrayOf(
            id_parte.toString()
        )
        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(
                scriptConsultaLectura,
                arregloParametrosConsultaLectura
            )

        val existeAlMenosUno = resultadoConsultaLectura
            .moveToFirst()
        val arregloRespuesta = arrayListOf<BPartes>()
        if (existeAlMenosUno) {
            do {
                val parte = BPartes(
                    resultadoConsultaLectura.getInt(0),
                    resultadoConsultaLectura.getInt(1),
                    resultadoConsultaLectura.getString(2),
                    resultadoConsultaLectura.getString(3),
                    resultadoConsultaLectura.getString(4),
                    resultadoConsultaLectura.getDouble(5)
                )
                arregloRespuesta.add(parte)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return if (arregloRespuesta.isNotEmpty()) arregloRespuesta[0] else null
    }
}
