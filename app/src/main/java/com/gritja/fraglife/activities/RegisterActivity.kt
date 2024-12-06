package com.gritja.fraglife.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.gritja.fraglife.R
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var sharedPrefs: SharedPreferences
    private var loggedIn = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            loggedIn = savedInstanceState.getBoolean("isLoggedIn")
        }
        setContentView(R.layout.activity_register)

        val toolbar: Toolbar = findViewById(R.id.nav_toolbar)
        setSupportActionBar(toolbar)

        val firstName = findViewById<EditText>(R.id.firstname)
        val lastName = findViewById<EditText>(R.id.lastname)
        val userEmail = findViewById<EditText>(R.id.useremail)
        val userName = findViewById<EditText>(R.id.username)
        val userPassword = findViewById<EditText>(R.id.userpassword)
        val registerButton = findViewById<Button>(R.id.register_button)
        val hasMeat = findViewById<CheckBox>(R.id.checkbox_meat)
        val kindOfCheese = findViewById<RadioGroup>(R.id.cheese_radio)

        registerButton.setOnClickListener {

            val uNamePattern = Pattern.compile("^[a-zA-Z0-9]+$")
            //checks alphanumeric
            val pWordPattern =
                Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
            //checks at least 1 uppercase, 1 lowercase, 1 digit, 1 special character
            //and at least 8 characters in length

            fun isValidUsername(username: String): Boolean {
                return uNamePattern.matcher(username).matches()
            }

            fun isValidPassword(password: String): Boolean {
                return pWordPattern.matcher(password).matches()
            }

            val userNameChosen = userName.text.toString()
            val passwordChosen = userPassword.text.toString()

            if (isValidUsername(userNameChosen) && isValidPassword(passwordChosen)) {
                val editor = sharedPrefs.edit()

                editor.putString("firstname", findViewById<EditText>(R.id.firstname).text.toString())
                editor.putString("lastname", findViewById<EditText>(R.id.firstname).text.toString())
                editor.putString("useremail", findViewById<EditText>(R.id.firstname).text.toString())
                editor.putString("username", findViewById<EditText>(R.id.username).text.toString())
                editor.putString("userpassword", findViewById<EditText>(R.id.userpassword).text.toString())
                editor.putBoolean("checkbox_meat", findViewById<CheckBox>(R.id.checkbox_meat).isChecked)
                editor.putInt("cheese_radio" , findViewById<RadioGroup>(R.id.cheese_radio).checkedRadioButtonId)
                editor.apply()
                Toast.makeText(this, "New user successfully registered!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "User names are alphanumeric only. Passwords need to be 8 characters long, contain 1 uppercase, 1 lowercase, 1 digit and 1 special character.", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        super.onPrepareOptionsMenu(menu)
        val menuItem2 = menu.findItem(R.id.action_second_activity)
        val menuItem3 = menu.findItem(R.id.action_third_activity)
        menuItem2.isVisible = loggedIn
        menuItem3.isVisible = loggedIn
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_login_activity -> {
                startActivity(Intent(this, MainActivity::class.java))
                intent.putExtra("isLoggedIn", loggedIn)
                true
            }

            R.id.action_second_activity -> {
                startActivity(Intent(this, SecondActivity::class.java))
                intent.putExtra("isLoggedIn", loggedIn)
                true
            }

            R.id.action_third_activity -> {
                startActivity(Intent(this, ThirdActivity::class.java))
                intent.putExtra("isLoggedIn", loggedIn)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}