package com.ajgroup.priceacsimulation.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajgroup.priceacsimulation.database.AcRepository
import java.lang.IllegalArgumentException

class HomeViewModelFactory(
    private  val repository: AcRepository,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")

    }
}