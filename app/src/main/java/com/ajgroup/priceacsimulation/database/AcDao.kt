package com.ajgroup.priceacsimulation.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AcDao {
    @Query("SELECT * FROM list_ac ")
    fun getAllAc(): LiveData<List<AcEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAc(ac: AcEntity)

    @Update
    suspend fun updateAc(ac: AcEntity)

    @Delete
    suspend fun deleteAc(ac: AcEntity)
}