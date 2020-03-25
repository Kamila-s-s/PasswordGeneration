package com.alkam.passwordgenerator

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_generator.*

class Generator : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generator)
        setSupportActionBar(findViewById(R.id.generator_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    fun applyPassword(view: View) {
        val applyPasswordIntent = Intent(this, MyPasswordsActivity::class.java)
        startActivity(applyPasswordIntent)
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
