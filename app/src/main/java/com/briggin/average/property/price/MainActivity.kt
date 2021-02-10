package com.briggin.average.property.price

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.briggin.average.property.price.view.PropertyFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, PropertyFragment.newInstance())
                .commit()
        }

    }
}
