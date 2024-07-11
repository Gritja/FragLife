package com.gritja.fraglife.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.gritja.fraglife.R

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.nav_toolbar)
        setSupportActionBar(toolbar)

        val userName = findViewById<EditText>(R.id.user_name)
        val userPassword = findViewById<EditText>(R.id.user_password)
        val registerButton = findViewById<Button>(R.id.register_button)
        val loginButton = findViewById<Button>(R.id.login_button)

        val userRegistered = intent.getBooleanExtra("userRegistered", false)
        if (userRegistered) {
            Toast.makeText(this, "User registered", Toast.LENGTH_LONG).show()
        }

        sharedPrefs = getSharedPreferences("user_credentials", Context.MODE_PRIVATE)

        /*userName.setText(sharedPrefs.getString("user_name"), ""))
        userPassword.setText(sharedPrefs.getString("user_password"), ""))

        loginButton.setOnClickListener {

            val userNameEntered = userName.text.toString()
            val passwordEntered = userPassword.text.toString()

            if (userNameEntered == "admin" && passwordEntered == "123") {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
                return@addSuccessListener
            }
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
       */

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        super.onPrepareOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.action_second_activity)
        menuItem.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.action_register_activity -> {
                    startActivity(Intent(this, RegisterActivity::class.java))
                    true
                }
                R.id.action_second_activity -> {
                    startActivity(Intent(this, SecondActivity::class.java))
                    true
                }
                R.id.action_third_activity ->{
                    startActivity(Intent(this, ThirdActivity::class.java))
                        true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
    override fun onPause() {
        super.onPause()

        val editor = sharedPrefs.edit()

        editor.putString("userName", findViewById<EditText>(R.id.user_name).text.toString())
        editor.putString("userPassword", findViewById<EditText>(R.id.user_password).text.toString())

        editor.apply()
    }
}
