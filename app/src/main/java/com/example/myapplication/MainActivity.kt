package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    internal lateinit var counter: TextView
    internal lateinit var increment: Button
    internal lateinit var reset: Button
    internal lateinit var close: Button

    internal var count = "LAST_COUNT"
    internal var key = "TV_VALUE"

    internal var textCounter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            textCounter = 0
        }

        reset = findViewById(R.id.reset)
        increment = findViewById(R.id.increment)
        close = findViewById(R.id.close)
        counter = findViewById(R.id.counter_add)
        counter.text = "0"

        reset.setOnClickListener(this)
        increment.setOnClickListener(this)
        close.setOnClickListener(this)

        readData()
    }

    fun readData() {
        val prefs = getSharedPreferences(key, Context.MODE_PRIVATE)
        textCounter = prefs.getInt(count, Integer.parseInt(counter.text.toString()))
        counter.text = "" + textCounter
    }

    fun saveData() {
        val prefs = getSharedPreferences(key, Context.MODE_PRIVATE)
        val editor = prefs.edit()

        editor.putInt(count, textCounter)
        editor.commit()
    }

    override fun onBackPressed() {
        saveData()
        super.onBackPressed()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.increment -> {

                textCounter++
                counter.text = textCounter.toString()
            }

            R.id.reset -> {

                counter.text = "0"
                textCounter = 0
            }

            R.id.close ->

                onBackPressed()
        }
    }
}
