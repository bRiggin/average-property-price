package com.briggin.average.property.price

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.briggin.average.property.price.presentation.PropertyViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: PropertyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
