package com.udacity.groupsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.Group
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var button: Button
    lateinit var group: Group

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        group = findViewById(R.id.group)

        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (group.visibility == View.GONE) {
            group.visibility = View.VISIBLE
            button.setText(R.string.hide_details)
        } else {
            group.visibility = View.GONE
            button.setText(R.string.show_details)
        }
    }
}
