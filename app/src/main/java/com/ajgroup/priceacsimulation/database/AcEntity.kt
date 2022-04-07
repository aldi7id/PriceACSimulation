package com.ajgroup.priceacsimulation.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Parcelize
@Entity(tableName = "list_ac")
class AcEntity(
    @PrimaryKey(autoGenerate = true)
    var userId: Int=0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name= "merk")
    var merk: String,
    @ColumnInfo(name = "harga")
    var harga: String,
): Parcelable