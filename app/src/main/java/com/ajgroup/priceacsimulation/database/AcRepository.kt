package com.ajgroup.priceacsimulation.database

import android.util.Log

class AcRepository(private val dao: AcDao) {

    val acs = dao.getAllAc()

    suspend fun insert(ac: AcEntity){
        return dao.addAc(ac)
    }
    suspend fun delete(ac: AcEntity){
        return dao.deleteAc(ac)
    }
    suspend fun edit(ac: AcEntity){
        return dao.updateAc(ac)
    }

}