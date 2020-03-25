package com.alkam.passwordgenerator

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_my_passwords.*

class MyPasswordsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_passwords)
        setSupportActionBar(findViewById(R.id.my_passwords_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun addRecords(view: View) {
        val addRecordsIntent = Intent(this, AddRecords::class.java)
        startActivity(addRecordsIntent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
