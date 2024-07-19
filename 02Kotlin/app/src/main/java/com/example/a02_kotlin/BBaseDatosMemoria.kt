package com.example.a02_kotlin

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Diego","a@a.com")
                )

            arregloBEntrenador
                .add(
                    BEntrenador(2,"Rafael","b@b.com")
                )

            arregloBEntrenador
                .add(
                    BEntrenador(3,"Carolina","c@c.com")
                )
        }
    }
}