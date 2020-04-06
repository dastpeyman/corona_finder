package com.md.coronatesting.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.md.coronatesting.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val result = intent.getStringExtra("result")
        if (result == "Positive")
            txtResult.text = resources.getString(R.string.result_bad)
        else
            txtResult.text = resources.getString(R.string.result_good)

        btnFinish.setOnClickListener { onBackPressed() }

    }

    override fun onBackPressed() {
        setResult(1)
        finish()
    }
}