package com.md.coronatesting.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.md.coronatesting.ApiServiceInterface
import com.md.coronatesting.R
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSurvey.setOnClickListener {
            showDialog()
        }
        btnMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        getAllStatistics()

    }

    private fun showDialog() {
        // Initialize a new instance of
        val builder = AlertDialog.Builder(this)

        // Set the alert dialog title
        builder.setTitle("")

        // Display a message on alert dialog
        builder.setMessage(resources.getString(R.string.message_alarm))

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton(resources.getString(R.string.continue_click)) { dialog, which ->
            // Do something when user press the positive button
            val intent = Intent(this, SurveyActivity::class.java)
            startActivity(intent)
            dialog.dismiss()
            // Change the app background color
        }

        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

    private fun getAllStatistics() {
        ApiServiceInterface.create()
            .getAllStatistics().enqueue(object : retrofit2.Callback<ResponseBody>  {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val temp = response.body()?.string()
                        val jsonObject = JSONObject(temp)
                        txtCases.text = jsonObject.get("cases").toString()
                        txtDeaths.text = jsonObject.get("deaths").toString()
                        txtRecover.text = jsonObject.get("recovered").toString()
                    }
                }
            })
    }
}
