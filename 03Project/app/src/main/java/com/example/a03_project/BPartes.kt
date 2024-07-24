package com.example.a03_project

import android.os.Parcel
import android.os.Parcelable

class BPartes (
    var id_parte:Int,
    var id_auto:Int,
    var nombre:String,
    var numero_serie:String,
    var fecha_fabricacion:String,
    var precio: Double
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble()
    ) {
    }

    override fun toString(): String {
        return "$id_auto ${nombre} $numero_serie $fecha_fabricacion $precio"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_parte)
        parcel.writeInt(id_auto)
        parcel.writeString(nombre)
        parcel.writeString(numero_serie)
        parcel.writeString(fecha_fabricacion)
        parcel.writeDouble(precio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BAutos> {
        override fun createFromParcel(parcel: Parcel): BAutos {
            return BAutos(parcel)
        }

        override fun newArray(size: Int): Array<BAutos?> {
            return arrayOfNulls(size)
        }
    }

}