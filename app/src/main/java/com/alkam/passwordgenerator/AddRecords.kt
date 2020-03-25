package com.alkam.passwordgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View

class AddRecords : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_records)
        setSupportActionBar(findViewById(R.id.add_record_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    fun savePassword(view: View) {
        val savePasswordIntent = Intent(this, MyPasswordsActivity::class.java)
        startActivity(savePasswordIntent)
    }

    fun generate(view: View) {
        val generateIntent = Intent(this, Generator::class.java)
        startActivity(generateIntent)
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
