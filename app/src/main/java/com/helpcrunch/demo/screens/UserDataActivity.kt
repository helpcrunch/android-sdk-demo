package com.helpcrunch.demo.screens

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.helpcrunch.demo.R
import com.helpcrunch.demo.databinding.ActivityUserDataBinding
import com.helpcrunch.library.core.Callback
import com.helpcrunch.library.core.HelpCrunch
import com.helpcrunch.library.core.models.user.HCUser

class UserDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            supportActionBar!!.setTitle(R.string.change_user_data)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        initViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() = with(binding) {
        val currentUser = HelpCrunch.getUser()
        if (currentUser != null) {
            nameEditText.setText(currentUser.name)
            emailEditText.setText(currentUser.email)
            phoneEditText.setText(currentUser.phone)
            userIdEditText.setText(currentUser.userId)
            companyEditText.setText(currentUser.company)
        }
        saveUserDataButton.setOnClickListener { updateUserData() }
        logoutButton.setOnClickListener { logout() }
    }

    private fun logout() {
        setLogoutButtonParameters(View.VISIBLE, false)

        HelpCrunch.logout(object : Callback<Any?>() {
            override fun onSuccess(result: Any?) {
                Toast.makeText(this@UserDataActivity, "Success", Toast.LENGTH_SHORT).show()
                setLogoutButtonParameters(View.GONE, false)
            }

            override fun onError(message: String) {
                Toast.makeText(this@UserDataActivity, message, Toast.LENGTH_SHORT).show()
                setLogoutButtonParameters(View.GONE, true)
            }
        })
    }

    private fun updateUserData() = with(binding) {
        val username = nameEditText.text.toString().trim { it <= ' ' }
        val email = emailEditText.text.toString().trim { it <= ' ' }
        val phone = phoneEditText.text.toString().trim { it <= ' ' }
        val company = companyEditText.text.toString().trim { it <= ' ' }
        val registerUserId = userIdEditText.text.toString().trim { it <= ' ' }

        if (username.trim { it <= ' ' }.isNotBlank()) {
            val customData = HashMap<String, Any>()

            val registerUser: HCUser = HCUser.Builder()
                .withUserId(registerUserId)
                .withName(username)
                .withEmail(email)
                .withPhone(phone)
                .withCustomData(customData)
                .withCompany(company)
                .build()

            setSaveUserDataButtonParameters(View.VISIBLE, false)
            val callback = object : Callback<HCUser>() {
                override fun onSuccess(result: HCUser) {
                    Toast.makeText(
                        this@UserDataActivity,
                        getString(R.string.data_saved),
                        Toast.LENGTH_SHORT
                    ).show()
                    setSaveUserDataButtonParameters(View.GONE, true)
                }

                override fun onError(message: String) {
                    Toast.makeText(this@UserDataActivity, message, Toast.LENGTH_SHORT).show()
                    setSaveUserDataButtonParameters(View.GONE, true)
                }
            }
            if (binding.logoutIfNecessary.isChecked) {
                HelpCrunch.forceUpdateUser(registerUser, callback)
            } else {
                HelpCrunch.updateUser(registerUser, callback)
            }
        } else {
            Toast.makeText(
                this@UserDataActivity,
                getString(R.string.error_id_is_empty),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setSaveUserDataButtonParameters(visible: Int, enabled: Boolean) = with(binding) {
        saveUserDataButtonProgress.visibility = visible
        saveUserDataButton.isEnabled = enabled
    }

    private fun setLogoutButtonParameters(visible: Int, enabled: Boolean) = with(binding) {
        logoutButtonProgress.visibility = visible
        logoutButton.isEnabled = enabled
    }
}