package com.ajgroup.priceacsimulation.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Register_users_table")
class RegisterEntity(
    @PrimaryKey(autoGenerate = true)
    var userId: Int=0,
    @ColumnInfo(name = "first_name")
    var firstName: String,
    @ColumnInfo(name= "last_name")
    var lastName: String,
    @ColumnInfo(name = "user_name")
    var userName: String,
    @ColumnInfo(name = "password_text")
    var passwrd: String
)