import java.io.File

data class Auto(
    var id: Int,
    val marca: String,
    val anioFabricacion: Int,
    val precio: Double,
    val electrico: Boolean
)

class AutoService {
    private val autos = mutableListOf<Auto>()

    fun createAuto(auto: Auto) {
        autos.add(auto)
    }


    fun readAllAutos(): List<Auto> {
        return autos
    }

    fun readAutoById(id: Int): Auto? {
        return autos.find { it.id == id }
    }

    fun updateAuto(auto: Auto) {
        val index = autos.indexOfFirst { it.id == auto.id }
        if (index != -1) {
            autos[index] = auto
        }
    }

    fun deleteAuto(auto: Auto) {
        autos.remove(auto)
    }

    // Guardar los datos en archivos de texto (.txt) y leerlos posteriormente
    fun saveAutosToFile(autosFolder: String) {
        val autosFile = File(autosFolder, "autos.txt")
        autosFile.bufferedWriter().use { out ->
            autos.forEach { auto ->
                out.write("${auto.id},${auto.marca},${auto.anioFabricacion},${auto.precio},${auto.electrico}\n")
            }
        }
    }

    fun loadAutosFromFile(autosFolder: String): List<Auto> {
        val autosFile = File(autosFolder, "autos.txt")
        val newAutos = mutableListOf<Auto>()

        if (!autosFile.exists()) {
            println("El archivo 'autos.txt' no existe en el directorio '$autosFolder'.")
            return newAutos
        }

        autosFile.bufferedReader().use { reader ->
            reader.forEachLine { line ->
                val parts = line.split(",")
                if (parts.size == 5) {
                    val id = parts[0].toInt()
                    val marca = parts[1]
                    val anioFabricacion = parts[2].toInt()
                    val precio = parts[3].toDouble()
                    val electrico = parts[4].toBoolean()
                    newAutos.add(Auto(id, marca, anioFabricacion, precio, electrico))
                } else {
                    println("Error al leer la línea: '$line'. Formato incorrecto.")
                }
            }
        }

        autos.addAll(newAutos) // Agregar los autos cargados al final de la lista existente
        return newAutos
    }
}