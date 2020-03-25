package com.alkam.passwordgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.alkam.passwordgenerator.database.PasswordDatabase
import com.alkam.passwordgenerator.viewmodels.PasswordViewModel
import com.alkam.passwordgenerator.viewmodels.PasswordViewModelFactory
import kotlinx.android.synthetic.main.activity_add_records.*

class AddRecords : AppCompatActivity() {
    private lateinit var viewModel: PasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_records)
        setSupportActionBar(findViewById(R.id.add_record_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val application = requireNotNull(this).application
        val dao = PasswordDatabase.getInstance(application).getDao()
        val viewModelFactory = PasswordViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PasswordViewModel::class.java)
    }

    fun savePassword(view: View) {
        if (correctFields()) {
            viewModel.addPassword(
                title_et.text.toString(),
                login_et.text.toString(),
                url_et.text.toString(),
                password_et.text.toString(),
                note_et.text.toString()
            )
            onBackPressed()
        } else {
            Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
        }
    }

    private fun correctFields(): Boolean {
        if (title_et.text.toString().isNotEmpty() && login_et.text.toString().isNotEmpty()
            && url_et.text.toString().isNotEmpty() && password_et.text.toString().isNotEmpty()
        )
            return true
        return false
    }

    fun generate(view: View) {
        val generateIntent = Intent(this, Generator::class.java)
        startActivityForResult(generateIntent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100) {
            val pwd: String? = data?.getStringExtra("password")
            if (pwd != null) {
                password_et.setText(pwd)
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
