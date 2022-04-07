package com.ajgroup.priceacsimulation.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ajgroup.priceacsimulation.database.AcEntity
import com.ajgroup.priceacsimulation.database.AcRepository
import com.ajgroup.priceacsimulation.database.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: AcRepository, application: Application):
    AndroidViewModel(application)
    {
        val acs = repository.acs
        init {
            Log.i("MYTAG","inside_users_Lisrt_init")
        }
        private val _navigateto = MutableLiveData<Boolean>()
        private val viewModelJob = Job()
        private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
        val navigateto: LiveData<Boolean>
            get() = _navigateto

        fun doneNavigating(){
            _navigateto.value=false
        }

        fun backButtonclicked(){
            _navigateto.value = true
        }
        fun inserData(ac: AcEntity){
            uiScope.launch {
            repository.insert(ac)
            }
        }
        fun editData(ac: AcEntity){
            uiScope.launch {
                repository.edit(ac)
            }
        }
        fun deleteData(ac: AcEntity){
            uiScope.launch {
                repository.delete(ac)
            }
        }
    }