package com.kimochisoft.practicalcaseone.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kimochisoft.practicalcaseone.R
import com.kimochisoft.practicalcaseone.common.UserSingleton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView3.text = "${UserSingleton.getUid()}\n${UserSingleton.getEmail()}"
    }
}