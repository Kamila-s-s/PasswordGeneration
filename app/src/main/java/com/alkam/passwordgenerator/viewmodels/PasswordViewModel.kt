package com.alkam.passwordgenerator.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.alkam.passwordgenerator.database.PasswordDAO
import com.alkam.passwordgenerator.models.PasswordModel
import kotlinx.coroutines.*


/**
 * ViewModel, которая хранит список паролей и методы для взаимодействия с ним
 */
class PasswordViewModel(val dao: PasswordDAO, application: Application) :
    AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _passwords = MutableLiveData<MutableList<PasswordModel>>()

    val password: LiveData<MutableList<PasswordModel>> get() = _passwords

    init {
        initPasswords()
    }

    private fun initPasswords() {
        uiScope.launch {
            _passwords.value = getPasswordsFromDB()
        }
    }

    private suspend fun getPasswordsFromDB(): MutableList<PasswordModel>? {
        return withContext(Dispatchers.IO) {
            val pwd = dao.getPasswords()
            pwd
        }
    }

    override fun onCleared() {
        super.onCleared()
        _passwords.value?.clear()
        viewModelJob.cancel()
    }

    fun addPassword(name: String, login: String, URL: String, password: String) {
        uiScope.launch {
            val pwd = PasswordModel(0L, name, login, URL, password)
            _passwords.value?.add(pwd)
            insert(pwd)
        }
    }

    private suspend fun insert(pwd: PasswordModel) {
        withContext(Dispatchers.IO) {
            dao.insertPassword(pwd)
        }
    }

    fun deletePassword(index: Int) {
        uiScope.launch {
            val pwd = _passwords.value!!.removeAt(index)
            delete(pwd)
        }
    }

    private suspend fun delete(pwd: PasswordModel) {
        withContext(Dispatchers.IO) {
            dao.deletePassword(pwd)
        }
    }
}

class PasswordViewModelFactory(private val dao: PasswordDAO, private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PasswordViewModel::class.java)) {
            return PasswordViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}