package com.ajgroup.priceacsimulation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RegisterEntity::class, AcEntity::class,], version = 2, exportSchema = false)
abstract class RegisterDatabase : RoomDatabase() {
    abstract val registerDatabaseDao: RegisterDatabaseDao
    abstract val acDao: AcDao

    companion object {

        @Volatile
        private var INSTANCE: RegisterDatabase? = null

        fun getInstance(context: Context) : RegisterDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RegisterDatabase::class.java,
                        "user_details_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}