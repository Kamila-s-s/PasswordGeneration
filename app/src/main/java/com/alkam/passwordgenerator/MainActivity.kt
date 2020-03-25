package com.alkam.passwordgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.main_toolbar))


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
