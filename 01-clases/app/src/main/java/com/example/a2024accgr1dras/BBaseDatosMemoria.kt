package com.example.a2024accgr1dras
import com.example.a2024accgr1dras.BEntrenador
class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Diego", "d@d.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Rafael", "r@r.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Carolina", "c@c.com")
                )
        }
    }
}