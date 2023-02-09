package com.example.android.petform

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val speciesSpinner: Spinner = findViewById(R.id.species_spinner)
        val adapter =
            ArrayAdapter.createFromResource(this, R.array.species, support_simple_spinner_dropdown_item)
            adapter.setDropDownViewResource(support_simple_spinner_dropdown_item)
        speciesSpinner.adapter=adapter
    }
}



