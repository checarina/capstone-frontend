package com.example.android.petform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class FormSubmitted : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_submitted)

        val text = findViewById<TextView>(R.id.formSubmittedMsg)
//        val extras = intent.extras
        val name = intent.getStringExtra("name")
        text.text = String.format("Profile for $name created!")
    }
}