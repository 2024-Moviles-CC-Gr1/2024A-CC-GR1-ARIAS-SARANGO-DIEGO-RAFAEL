import java.io.File
import java.text.SimpleDateFormat
import java.util.*

data class Parte(
    var autoId: Int,
    val nombre: String,
    var numeroSerie: String,
    val fechaFabricacion: Date,
    val precio: Double
)

class ParteService {
    private val partes = mutableListOf<Parte>()

    fun createParte(parte: Parte) {
        partes.add(parte)
    }

    fun readPartesByAutoId(autoId: Int): List<Parte> {
        return partes.filter { it.autoId == autoId }
    }

    fun readAllPartes(): List<Parte> {
        return partes
    }


    fun readParteById(autoId: Int, numeroSerie: String): Parte? {
        return partes.find { it.autoId == autoId && it.numeroSerie == numeroSerie }
    }

    fun updateParte(parte: Parte) {
        val index = partes.indexOfFirst { it.autoId == parte.autoId && it.numeroSerie == parte.numeroSerie }
        if (index != -1) {
            partes[index] = parte
        }
    }

    fun deleteParte(parte: Parte) {
        partes.remove(parte)
    }

    fun savePartesToFile(partesFolder: String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val partesFile = File(partesFolder, "partes.txt")
        partesFile.bufferedWriter().use { out ->
            partes.forEach { parte ->
                val fechaFabricacionString = dateFormat.format(parte.fechaFabricacion)
                out.write("${parte.autoId},${parte.nombre},${parte.numeroSerie},$fechaFabricacionString,${parte.precio}\n")
            }
        }
    }

    fun loadPartesFromFile(partesFolder: String): List<Parte> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val partesFile = File(partesFolder, "partes.txt")
        val newPartes = mutableListOf<Parte>()

        if (!partesFile.exists()) {
            println("El archivo 'partes.txt' no existe en el directorio '$partesFolder'.")
            return newPartes
        }

        partesFile.bufferedReader().use { reader ->
            reader.readLines().forEach { line ->
                val parts = line.split(",")
                if (parts.size == 5) {
                    val autoId = parts[0].toInt()
                    val nombre = parts[1]
                    val numeroSerie = parts[2]
                    val fechaFabricacionString = parts[3]
                    val fechaFabricacion = dateFormat.parse(fechaFabricacionString)
                    val precio = parts[4].toDouble()
                    newPartes.add(Parte(autoId, nombre, numeroSerie, fechaFabricacion, precio))
                } else {
                    println("Error al leer la línea: '$line'. Formato incorrecto.")
                }
            }
        }

        partes.addAll(newPartes) // Agregar las partes cargadas al final de la lista existente
        return newPartes
    }
}