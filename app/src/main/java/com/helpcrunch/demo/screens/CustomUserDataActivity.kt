package com.helpcrunch.demo.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.helpcrunch.demo.R
import com.helpcrunch.demo.databinding.ActivityCustomUserDataBinding
import com.helpcrunch.library.core.Callback
import com.helpcrunch.library.core.HelpCrunch.getUser
import com.helpcrunch.library.core.HelpCrunch.updateUser
import com.helpcrunch.library.core.models.user.HCUser

class CustomUserDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomUserDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomUserDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        fillData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_custom_data, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        } else if (item.itemId == R.id.add_row) {
            addRow(null, null)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() = with(binding){
        if (supportActionBar != null) {
            supportActionBar!!.setTitle(R.string.change_custom_user_data)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        saveUserDataButton.setOnClickListener { saveCustomData() }
    }

    private fun fillData() {
        val currentUser = getUser() ?: return
        val data = currentUser.customData
        if (data != null) {
            for ((key, value) in data) {
                addRow(key, value.toString())
            }
        }
    }

    private fun saveCustomData() {
        if (getUser() == null) {
            Toast.makeText(this, "There is no user yet", Toast.LENGTH_SHORT).show()
            return
        }
        val customData = HashMap<String, Any?>()
        getNewData(customData, binding.registrationForm)
        var user = getUser()
        if (user == null) {
            user = HCUser.Builder().build()
        }
        user.customData = customData
        setUserDataButtonParameters(View.VISIBLE, false)
        updateUser(user, object : Callback<HCUser>() {
            override fun onSuccess(result: HCUser) {
                Toast.makeText(
                    this@CustomUserDataActivity,
                    getString(R.string.data_saved),
                    Toast.LENGTH_SHORT
                ).show()
                setUserDataButtonParameters(View.GONE, true)
            }

            override fun onError(message: String) {
                Toast.makeText(
                    this@CustomUserDataActivity,
                    getString(R.string.something_wrong),
                    Toast.LENGTH_SHORT
                ).show()
                setUserDataButtonParameters(View.GONE, true)
            }
        })
    }

    private fun getNewData(customData: HashMap<String, Any?>, form: LinearLayout) {
        for (i in 0 until form.childCount) {
            val child = form.getChildAt(i)
            if (child is LinearLayout) {
                val key = (child.findViewById<View>(R.id.key) as EditText).text.toString()
                val value = (child.findViewById<View>(R.id.value) as EditText).text.toString()
                addData(customData, key, value)
            }
        }
    }

    private fun addRow(key: String?, value: String?) {
        val view = LayoutInflater.from(this).inflate(R.layout.layout_custom_user_data_row, null)
        (view.findViewById<View>(R.id.key) as EditText).setText(key)
        (view.findViewById<View>(R.id.value) as EditText).setText(value)
        binding.registrationForm.addView(view)
    }

    private fun addData(customData: MutableMap<String, Any?>, key: String, value: String) {
        if (key.isNotBlank() && value.isNotBlank()) {
            customData[key] = value
        }
    }

    private fun setUserDataButtonParameters(visible: Int, enabled: Boolean) {
        findViewById<View>(R.id.save_user_data_button_progress).visibility = visible
        findViewById<View>(R.id.save_user_data_button).isEnabled = enabled
    }
}