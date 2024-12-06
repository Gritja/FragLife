package com.gritja.fraglife.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.gritja.fraglife.R

class SecondActivity : AppCompatActivity() {
    private lateinit var sharedPrefs: SharedPreferences
    private var loggedIn = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            loggedIn = savedInstanceState.getBoolean("isLoggedIn")
        }
        setContentView(R.layout.activity_second)

        val toolbar: Toolbar = findViewById(R.id.nav_toolbar)
        setSupportActionBar(toolbar)

        sharedPrefs = getSharedPreferences("user_credentials", Context.MODE_PRIVATE)

        }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        super.onPrepareOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.action_third_activity)
        menuItem.isVisible = loggedIn
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_login_activity -> {
                startActivity(Intent(this, MainActivity::class.java))
                intent.putExtra("isLoggedIn", loggedIn)
                true
            }

            R.id.action_register_activity -> {
                startActivity(Intent(this, RegisterActivity::class.java))
                intent.putExtra("isLoggedIn", loggedIn)
                true
            }

            R.id.action_third_activity -> {
                startActivity(Intent(this, SecondActivity::class.java))
                intent.putExtra("isLoggedIn", loggedIn)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}