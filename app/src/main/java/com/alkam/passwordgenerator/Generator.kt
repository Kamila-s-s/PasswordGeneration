package com.alkam.passwordgenerator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_generator.*

class Generator : AppCompatActivity() {
    private var passwordLength: Int = 8
    private var currentPassword: String = "ВАШ ПАРОЛЬ"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generator)
        setSupportActionBar(findViewById(R.id.generator_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        updateCount()
        updatePassword()
    }

    fun applyPassword(view: View) {
        val intent = Intent()
        intent.putExtra("password", currentPassword)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun generate(view: View) {
        if (lowercase_cb.isChecked or
            uppercase_cb.isChecked or
            digit_cb.isChecked or
            symbol_cb.isChecked
        ) {
            currentPassword = generetor(
                lowercase_cb.isChecked,
                uppercase_cb.isChecked,
                digit_cb.isChecked,
                symbol_cb.isChecked
            )
            updatePassword()
        } else {
            Toast.makeText(this, "Хотя бы один пунтк должен быть выбран", Toast.LENGTH_SHORT).show()
        }
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

    fun onUpClick(view: View) {
        if (passwordLength < 20) {
            passwordLength += 1
            updateCount()
        }
    }

    fun onDownClick(view: View) {
        if (passwordLength > 4) {
            passwordLength -= 1
            updateCount()
        }
    }

    private fun updatePassword() {
        password_textView.text = currentPassword
    }

    private fun updateCount() {
        popup_menu.text = passwordLength.toString()
    }

    fun generetor(LCL_flag: Boolean, UCL_flag: Boolean, N_flag: Boolean, SC_flag: Boolean): String {
        val LOWERCASE_LETTERS = "a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" ")
        val UPPERCASE_LETTERS = "A B C D E F J H I G K L M N O P Q R S T U V W X Y Z".split(" ")
        val NUMBERS = "1 2 3 4 5 6 7 8 9 0".split(" ")
        val SPECIAL_CHARACTERS = "! @ # $ % ^ & *".split(" ")

        val SET_FOR_PASSWORD: MutableList<CharSequence> = mutableListOf()

        if (LCL_flag) {
            SET_FOR_PASSWORD.addAll(LOWERCASE_LETTERS)
        }
        if (UCL_flag) {
            SET_FOR_PASSWORD.addAll(UPPERCASE_LETTERS)
        }
        if (N_flag) {
            SET_FOR_PASSWORD.addAll(NUMBERS)
        }
        if (SC_flag) {
            SET_FOR_PASSWORD.addAll(SPECIAL_CHARACTERS)
        }
        val password = (SET_FOR_PASSWORD).shuffled().take(passwordLength)
        return password.joinToString("")
    }

}
