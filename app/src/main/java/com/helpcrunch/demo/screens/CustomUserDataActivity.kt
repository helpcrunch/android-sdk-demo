package com.helpcrunch.demo.screens

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                insets.left,
                insets.top,
                insets.right,
                insets.bottom
            )

            WindowInsetsCompat.CONSUMED
        }

        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true

        setSupportActionBar(binding.toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.setTitle(R.string.send_message)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        binding.saveUserDataButton.setOnClickListener { saveCustomData() }
        binding.addNewItem.setOnClickListener { addRow(null, null) }
    }

    private fun fillData() {
        val data = getUser()?.customData ?: return

        if (data.isNotEmpty()) {
            for ((key, value) in data) {
                addRow(key, value.toString())
            }
        } else {
            addRow(null, null)
        }
    }

    private fun saveCustomData() {
        binding.saveUserDataButtonIcon.setLoading(true)
        binding.saveUserDataButton.isEnabled = false

        val customData = HashMap<String, Any?>()
        getNewData(customData, binding.registrationForm)
        val user = HCUser.Builder(getUser())
            .withCustomData(customData)
            .build()
        updateUser(user, object : Callback<HCUser>() {
            override fun onSuccess(result: HCUser) {
                Toast.makeText(
                    this@CustomUserDataActivity,
                    getString(R.string.data_saved),
                    Toast.LENGTH_SHORT
                ).show()

                binding.saveUserDataButtonIcon.setLoading(false)
                binding.saveUserDataButton.isEnabled = true
            }

            override fun onError(message: String) {
                Toast.makeText(
                    this@CustomUserDataActivity,
                    getString(R.string.something_wrong),
                    Toast.LENGTH_SHORT
                ).show()

                binding.saveUserDataButtonIcon.setLoading(false)
                binding.saveUserDataButton.isEnabled = true
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

    private fun addData(customData: MutableMap<String, Any?>, key: String, item: String) {
        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(item)) {
            customData[key] = item
        }
    }
}
