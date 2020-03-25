package com.alkam.passwordgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alkam.passwordgenerator.database.PasswordDatabase
import com.alkam.passwordgenerator.viewmodels.PasswordViewModel
import com.alkam.passwordgenerator.viewmodels.PasswordViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.main_toolbar))

        val application = requireNotNull(this).application
        val dao = PasswordDatabase.getInstance(application).getDao()
        val viewModelFactory = PasswordViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PasswordViewModel::class.java)


    }

    fun addRecords(view: View) {
        val addRecordsIntent = Intent(this, AddRecords::class.java)
        startActivity(addRecordsIntent)
    }

    fun myPasswords(view: View) {
        val myPasswordsIntent = Intent(this, MyPasswordsActivity::class.java)
        startActivity(myPasswordsIntent)
    }
}
