package com.alkam.passwordgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AddRecords : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_records)
        setSupportActionBar(findViewById(R.id.add_record_toolbar))
    }
}
