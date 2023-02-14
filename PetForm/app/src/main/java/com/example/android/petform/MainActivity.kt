package com.example.android.petform

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.*
import androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
import androidx.appcompat.app.AppCompatActivity
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






        findViewById<Button>(R.id.submit_button).setOnClickListener {
            submitButtonHandler()
            Toast.makeText(this, "this button doesn't do anything yet", Toast.LENGTH_LONG).show()
        }
        val speciesSpinner: Spinner = findViewById(R.id.species_spinner)
        val adapter = ArrayAdapter.createFromResource(this, R.array.species, support_simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(support_simple_spinner_dropdown_item)
        speciesSpinner.adapter=adapter
        val submitButton = findViewById<Button>(R.id.submit_button)
    }



    fun submitButtonHandler() {
        val name = findViewById<EditText>(R.id.nameField)
        val sexSelect = findViewById<RadioGroup>(R.id.sexSelect).checkedRadioButtonId

        val sex = findViewById<RadioButton>(sexSelect)
        val species = findViewById<Spinner>(R.id.species_spinner)
        val age = findViewById<EditText>(R.id.ageField)

        val formBody = FormBody.Builder()
        .add("name", name.toString())
        .add("sex", sex.toString())
        .add("species", species.toString())
        .add("age", age.toString())
        .build()
        val request = Request.Builder()
            .url("https://carina-capstone-backend.herokuapp.com/pets")
            .post(formBody)
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")}

    }


}



