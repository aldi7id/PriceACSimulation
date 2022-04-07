package com.ajgroup.priceacsimulation.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajgroup.priceacsimulation.database.RegisterRepository
import java.lang.IllegalArgumentException

class RegiterViewModelFactory(
    private  val repository: RegisterRepository,
    private val application: Application
): ViewModelProvider.Factory
 {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
         if(modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
             return RegisterViewModel(repository, application) as T
         }
         throw IllegalArgumentException("Unknown View Model Class")
     }
}