package com.ajgroup.priceacsimulation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegisterDao{
    @Insert
    suspend fun insert(register: RegisterEntity)

    @Query("DELETE FROM Register_users_table")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM Register_users_table WHERE user_name LIKE :userName")
    suspend fun getUsername(userName: String): RegisterEntity?

}