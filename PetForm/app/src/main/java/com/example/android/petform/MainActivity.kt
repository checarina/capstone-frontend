package com.example.android.petform

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.View
import android.widget.*
import androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class MainActivity : AppCompatActivity() {



    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StrictMode.setThreadPolicy(
            ThreadPolicy.Builder()
                .permitAll()
                .build()
        )


//        findViewById<Button>(R.id.submit_button).setOnClickListener {
//            submitButtonHandler()
//            Toast.makeText(this, "this button doesn't do anything yet", Toast.LENGTH_LONG).show()
//        }
        val speciesSpinner: Spinner = findViewById(R.id.species_spinner)
        val adapter = ArrayAdapter.createFromResource(this, R.array.species, support_simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(support_simple_spinner_dropdown_item)
        speciesSpinner.adapter=adapter
        val submitButton = findViewById<Button>(R.id.submit_button)
        submitButton.setOnClickListener {submitButtonHandler()}

    }


    fun submitButtonHandler() {


        val name = findViewById<EditText>(R.id.nameField).text
        val sexSelect = findViewById<RadioGroup>(R.id.sexSelect).checkedRadioButtonId

        val sex = findViewById<RadioButton>(sexSelect).text
        val species = findViewById<Spinner>(R.id.species_spinner).selectedItem
        val age = findViewById<EditText>(R.id.ageField).text

//        val formBody = FormBody.Builder()
//        .add("name", name.toString())
//        .add("sex", sex.toString())
//        .add("species", species.toString())
//        .add("age", age.toString())
//        .build()

        val petData = """
             {"name":"${name.toString()}",
    "sex":"${sex.toString()}",
    "species":"${species.toString()}",
    "age":"${age.toString()}"}
        """.trimIndent()

        val mediaType = "application/json; charset=utf-8".toMediaType()

        val body = petData.toRequestBody(mediaType)
        val request = Request.Builder()
            .url("https://carina-capstone-backend.herokuapp.com/pets")
            .post(body)
            .build()


//
//                }
//        val formBody = RequestBody.create(
//            MediaType.Companion.parse(), petData
//        )
//
//
//        val request = Request.Builder()
//            .url("https://carina-capstone-backend.herokuapp.com/pets")
//            .post(formBody)
//            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")}

    }


}



