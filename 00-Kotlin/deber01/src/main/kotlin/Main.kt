import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val autoService = AutoService()
    val parteService = ParteService()
    val autosFolder = "autos"
    val partesFolder = "partes"

    // Crear las carpetas si no existen
    File(autosFolder).mkdirs()
    File(partesFolder).mkdirs()

    // Cargar datos desde archivos al iniciar el programa
    autoService.loadAutosFromFile(autosFolder)
    parteService.loadPartesFromFile(partesFolder)

    val scanner = Scanner(System.`in`)

    var exitProgram = false

    while (!exitProgram) {
        println("--- Menu principal ---")
        println("1. Gestion de autos")
        println("2. Gestion de partes")
        println("3. Salir")
        print("Seleccione una opción: ")
        when (scanner.nextInt()) {
            1 -> {
                var exitAutosMenu = false
                while (!exitAutosMenu) {
                    println("\n-- Menu Autos --")
                    println("1. Crear auto")
                    println("2. Ver autos")
                    println("3. Editar auto")
                    println("4. Eliminar auto")
                    println("5. Salir")
                    print("Seleccione una opción: ")
                    when (scanner.nextInt()) {
                        1 -> {
                            println("\n-- Crear Auto --")
                            val auto = inputAuto(scanner)
                            autoService.createAuto(auto)
                            println("Auto creado exitosamente.")
                        }
                        2 -> {
                            println("\n-- Ver Autos --")
                            autoService.readAllAutos().forEach { println(it) }
                        }
                        3 -> {
                            println("\n-- Editar Auto --")
                            println("Ingrese el ID del auto a editar: ")
                            val id = scanner.nextInt()
                            val auto = autoService.readAutoById(id)
                            if (auto != null) {
                                val updatedAuto = inputAuto(scanner)
                                updatedAuto.id = id
                                autoService.updateAuto(updatedAuto)
                                println("Auto actualizado exitosamente.")
                            } else {
                                println("No se encontró un auto con ese ID.")
                            }
                        }
                        4 -> {
                            println("\n-- Eliminar Auto --")
                            println("Ingrese el ID del auto a eliminar: ")
                            val id = scanner.nextInt()
                            val auto = autoService.readAutoById(id)
                            if (auto != null) {
                                autoService.deleteAuto(auto)
                                println("Auto eliminado exitosamente.")
                            } else {
                                println("No se encontró un auto con ese ID.")
                            }
                        }
                        5 -> exitAutosMenu = true
                        else -> println("Opción no válida.")
                    }
                }
            }
            2 -> {
                var exitPartesMenu = false
                while (!exitPartesMenu) {
                    println("\n-- Menu Partes --")
                    println("1. Crear parte")
                    println("2. Ver partes")
                    println("3. Editar parte")
                    println("4. Eliminar parte")
                    println("5. Salir")
                    print("Seleccione una opción: ")
                    when (scanner.nextInt()) {
                        1 -> {
                            println("\n-- Crear Parte --")
                            val parte = inputParte(scanner)
                            parteService.createParte(parte)
                            println("Parte creada exitosamente.")
                        }
                        2 -> {
                            println("\n-- Ver Partes --")
                            parteService.readAllPartes().forEach { println(it) }
                        }
                        3 -> {
                            println("\n-- Editar Parte --")
                            println("Ingrese el ID del auto al que pertenece la parte: ")
                            val autoId = scanner.nextInt()
                            println("Ingrese el número de serie de la parte: ")
                            val numeroSerie = scanner.next()
                            val parte = parteService.readParteById(autoId, numeroSerie)
                            if (parte != null) {
                                val updatedParte = inputParte(scanner)
                                updatedParte.autoId = autoId
                                updatedParte.numeroSerie = numeroSerie
                                parteService.updateParte(updatedParte)
                                println("Parte actualizada exitosamente.")
                            } else {
                                println("No se encontró una parte con esos datos.")
                            }
                        }
                        4 -> {
                            println("\n-- Eliminar Parte --")
                            println("Ingrese el ID del auto al que pertenece la parte: ")
                            val autoId = scanner.nextInt()
                            println("Ingrese el número de serie de la parte: ")
                            val numeroSerie = scanner.next()
                            val parte = parteService.readParteById(autoId, numeroSerie)
                            if (parte != null) {
                                parteService.deleteParte(parte)
                                println("Parte eliminada exitosamente.")
                            } else {
                                println("No se encontró una parte con esos datos.")
                            }
                        }
                        5 -> exitPartesMenu = true
                        else -> println("Opción no válida.")
                    }
                }
            }
            3 -> {
                // Guardar datos en archivos al salir del programa
                autoService.saveAutosToFile(autosFolder)
                parteService.savePartesToFile(partesFolder)
                exitProgram = true
                println("Saliendo del programa...")
            }
            else -> println("Opción no válida.")
        }
    }

    scanner.close()
}

fun inputAuto(scanner: Scanner): Auto {
    println("Creación de nuevo auto:")
    print("ID del auto (automático): ")
    val id = scanner.nextInt() // No es necesario, pero se solicita para mantener el formato
    print("Marca del auto: ")
    val marca = scanner.next()
    print("Año de fabricación: ")
    val anioFabricacion = scanner.nextInt()
    print("Precio del auto: ")
    val precio = scanner.nextDouble()
    print("¿Es eléctrico? (true/false): ")
    val electrico = scanner.nextBoolean()
    return Auto(id, marca, anioFabricacion, precio, electrico)
}


fun inputParte(scanner: Scanner): Parte {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    print("ID del auto al que pertenece la parte: ")
    val autoId = scanner.nextInt()
    print("Nombre de la parte: ")
    val nombre = scanner.next()
    print("Número de serie: ")
    val numeroSerie = scanner.next()

    var fechaFabricacion: Date? = null
    var fechaValida = false
    while (!fechaValida) {
        print("Fecha de fabricación (formato YYYY-MM-DD): ")
        val fechaFabricacionStr = scanner.next()

        try {
            fechaFabricacion = dateFormat.parse(fechaFabricacionStr)
            fechaValida = true
        } catch (e: Exception) {
            println("Error al analizar la fecha. Asegúrate de seguir el formato YYYY-MM-DD.")
        }
    }

    print("Precio de la parte: ")
    val precio = scanner.nextDouble()

    return Parte(autoId, nombre, numeroSerie, fechaFabricacion!!, precio)
}