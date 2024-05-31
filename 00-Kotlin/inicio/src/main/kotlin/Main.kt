import java.util.*
import kotlin.collections.ArrayList

fun main() {
    println("Hola mundo")
    // INMUTABILIDAD
    // No se RE ASIGNA "="
    val inmutable: String = "Diego"
    // inmutable = "Rafael"; //Error!
    var mutable: String = "Rafael"
    mutable = "Diego" //OK
    // VAL > VAR

    // Duck Typing
    var ejemploVariable = "Diego Arias"
    val edadEjemplo: Int = 12
    ejemploVariable.trim()
    // ejemploVariable = edadEjemplo // Error!


    // Variables Primitivas
    val nombreProfesor: String = "Diego Arias"
    val sueldo: Double = 5.5
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true
    //Clases en Java
    val fechaNacimiento: Date = Date()

    //No existe el Switch, pero tiene su similar WHEN
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C")->{
            println("Casado")
        }
        "S" ->{
            println("Soltero")
        }
        else ->{
            println("No sabemos")
        }
    }
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.00)
    // Named parameters
    // calcularSueldo(sueldo, tasa, bonoEspecial)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo=10.00, tasa=14.00)



    //PROBAR LOS FUNCIONES
    val sumaUno = Suma (1,1)
    val sumaDos = Suma (null, 1)
    val sumaTres = Suma (1, null)
    val sumaCuatro = Suma (null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)







    //ARREGLOS
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico);
    //DINAMICOS
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10
    )
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)


    // FOR EACH = > UNIT
    // ITERAR UN ARREGLO
    val respuestaForEach: Unit = arregloDinamico
        .forEach{ valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    // it (en ingles "eso") significa el elemento iterado
    arregloDinamico.forEach{println("Valor Actual (it): ${it}")}


    // MAP -> MUTA (modifica) el arreglo
    // 1) Enviamos el nuevo valor de la iteración
    // 2) Nos devuelve un NUEVO Arreglo con valores de las iteraciones
    val respuestaMap: List<Double> = arregloDinamico
        .map{valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map{it + 15}
    println(respuestaMapDos)


    // Operador Filter -> Filtrar el ARREGLO
    // 1) Devolver una expresión (TRUE o FALSE)
    // 2) Nuevo arreglo FILTRADO
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            // Expresión o Condición
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter {  it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    // OPERADORES OR Y AND
    // OR -> ANY (Alguno cumple?)
    // AND -> ALL (Todos cumplen?)
    val respuestaAny: Boolean = arregloDinamico
        .any{valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) // True
    val respuestaAll: Boolean = arregloDinamico
        .any{valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAll) // False



    // OPERADOR REDUCE
    // REDUCE -> Valor Acumulado
    // Valor acumulado = 0 (siempre empieza en 0 en Kotlin)
    // (1,2,3,4,5) -> Acumular "SUMAR" estos valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 +1 = 1 -> Iteracion1
    // valorIteracion2 = valorAcumuladoIteracion1 + 2 = 1 +2 = 3 -> Iteracion2
    val respuestaReduce: Int = arregloDinamico
        .reduce{ acumulado: Int, valorActual: Int ->
            // Sentencia del negocio
            return@reduce(acumulado + valorActual)
        }
    println(respuestaReduce);
    // return@reduce acumulado + (itemCarrito.cantidad + itemCarrito.precio)

}


//void -> Unit
fun imprimirNombre(nombre:String): Unit{
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, // Opcional (defecto)
    bonoEspecial:Double? = null // Opcional (nullable)
    // Variable? -> "?" Es Nulleable (osea que en algun momento va a ser nulo)
):Double{
    // Int -> Int? (nullable)
    // String -> String? (nullable)
    // Date -> Date? (nullable)
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) * bonoEspecial
    }
}

//Estructura en java
abstract  class NumeroJava{
    protected  val numeroUno:Int
    private val numerDos: Int
    constructor(
        uno: Int,
        dos: Int
    ){
        this.numeroUno = uno
        this.numerDos = dos
        println("Inicializando")
    }
}

//Estructura en kotlin
abstract class Numeros(
    protected val numeroUno:Int,
    protected val numeroDos:Int,
){
    init { //Bloque de constructor primario
        println("Inicializando")
    }
}

class Suma(
    unoParametro: Int,
    dosParametro: Int,
): Numeros(
    unoParametro,
    dosParametro
){
    public val soyPublicoExplicito: String = "Explicito" // Publicas
    val soyPublicoImplicito:String = "Implicito" //Publicos (propiedades, metodos)
    init{ //Bloque Codigo Constructor primario
        this.numeroUno
        this.numeroDos
        numeroUno // this. OPCIONAL (propiedades, metodos)
        numeroDos // this. OPCIONAL (propiedades, metodos)
        this.soyPublicoExplicito
        soyPublicoImplicito // this. OPCIONAL (propiedades, metodos)
    }

    // USO DE CONSTRUCTORES
    constructor( //constructor secundario
        uno: Int?,
        dos: Int
    ):this(
        if(uno == null) 0 else uno,
        dos
    )

    constructor(
        uno:Int,
        dos:Int?
    ):this(
        uno,
        if(dos == null) 0 else dos,
    )

    constructor(
        uno:Int?,
        dos:Int?
    ):this(
        if(uno == null) 0 else uno,
        if(dos == null) 0 else dos,
    )

    // public fun sumar():Int {opcional "public")
    fun sumar():Int{
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }
    companion object{
        // funciones y variables
        val pi = 3.14
        fun elevarAlCuadrado (num:Int):Int{
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorTotalSuma:Int){
            historialSumas.add(valorTotalSuma)
        }
    }

}
