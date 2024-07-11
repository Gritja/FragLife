package com.gritja.fraglife.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.gritja.fraglife.R
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val toolbar: Toolbar = findViewById(R.id.nav_toolbar)
        setSupportActionBar(toolbar)

        val firstName = findViewById<EditText>(R.id.firstname)
        val lastName = findViewById<EditText>(R.id.lastname)
        val userName = findViewById<EditText>(R.id.username)
        val userPassword = findViewById<EditText>(R.id.userpassword)
        val registerButton = findViewById<Button>(R.id.registerbutton)

        registerButton.setOnClickListener {

            val uNamePattern = Pattern.compile("^[a-zA-Z0-9]+$")
            //checks alphanumeric
            val pWordPattern =
                Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
//checks at least 1 uppercase, 1 lowercase, 1 digit, 1 special character and at least 8 characters in length

            fun isValidUsername(username: String): Boolean {
                return uNamePattern.matcher(username).matches()
            }

            fun isValidPassword(password: String): Boolean {
                return pWordPattern.matcher(password).matches()
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        super.onPrepareOptionsMenu(menu)
        val menuItemi = menu.findItem(R.id.action_second_activity)
        //menuItem.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_login_activity -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }

            R.id.action_second_activity -> {
                startActivity(Intent(this, SecondActivity::class.java))
                true
            }

            R.id.action_third_activity -> {
                startActivity(Intent(this, ThirdActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}