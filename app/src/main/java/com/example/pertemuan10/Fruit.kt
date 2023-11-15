package com.example.pertemuan10

import android.os.Parcel
import android.os.Parcelable

data class Fruit(

    // * MODEL FRUIT

    val image: Int,
    val banner: Int,
    val nama: String?,
    val namaLatin: String?,
    val warna: String?,
    val keterangan: String?,

    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeInt(banner)
        parcel.writeString(nama)
        parcel.writeString(namaLatin)
        parcel.writeString(warna)
        parcel.writeString(keterangan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Fruit> {
        override fun createFromParcel(parcel: Parcel): Fruit {
            return Fruit(parcel)
        }

        override fun newArray(size: Int): Array<Fruit?> {
            return arrayOfNulls(size)
        }
    }
}
