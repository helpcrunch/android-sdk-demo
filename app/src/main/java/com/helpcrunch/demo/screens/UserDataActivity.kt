package com.helpcrunch.demo.screens

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.helpcrunch.demo.R
import com.helpcrunch.demo.databinding.ActivityUserDataBinding
import com.helpcrunch.library.core.Callback
import com.helpcrunch.library.core.HelpCrunch
import com.helpcrunch.library.core.HelpCrunch.forceUpdateUser
import com.helpcrunch.library.core.HelpCrunch.updateUser
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
            setUser(currentUser)
        }
        saveUserDataButton.setOnClickListener { updateUserData() }
        logoutButton.setOnClickListener { logout() }
    }

    private fun logout() = with(binding) {
        logoutButtonIcon.setLoading(true)
        logoutButton.isEnabled = false
        saveUserDataButton.isEnabled = false

        HelpCrunch.logout(object : Callback<Any?>() {
            override fun onSuccess(result: Any?) {
                Toast.makeText(this@UserDataActivity, "Success", Toast.LENGTH_SHORT).show()
                logoutButtonIcon.setLoading(false)
                logoutButton.isEnabled = true
                saveUserDataButton.isEnabled = true
            }

            override fun onError(message: String) {
                Toast.makeText(this@UserDataActivity, message, Toast.LENGTH_SHORT).show()
                logoutButtonIcon.setLoading(false)
                logoutButton.isEnabled = true
                saveUserDataButton.isEnabled = true
            }
        })
    }

    private fun updateUserData() {
        val registerUserId = binding.userIdEditText.text.toString()
        showProgress(true)
        doUpdateUser(registerUserId)
    }

    private fun doUpdateUser(registerUserId: String) {
        val username = binding.nameEditText.text.toString()

        val registerUser = HCUser.Builder()
            .withName(username)
            .withEmail(binding.emailEditText.text.toString())
            .withPhone(binding.phoneEditText.text.toString())
            .withUserId(registerUserId)
            .withCompany(binding.companyEditText.text.toString())
            .build()

        val callback = object : Callback<HCUser>() {
            override fun onSuccess(result: HCUser) {
                Toast.makeText(
                    this@UserDataActivity,
                    getString(R.string.data_saved),
                    Toast.LENGTH_SHORT
                ).show()
                showProgress(false)
            }

            override fun onError(message: String) {
                Toast.makeText(this@UserDataActivity, message, Toast.LENGTH_SHORT)
                    .show()
                showProgress(false)
            }
        }

        if (binding.logoutIfNecessary.isChecked) {
            forceUpdateUser(registerUser, callback)
        } else {
            updateUser(registerUser, callback)
        }
    }

    private fun showProgress(isProgress: Boolean) = with(binding) {
        saveUserDataButtonIcon.setLoading(isProgress)
        saveUserDataButton.isEnabled = isProgress.not()
    }

    private fun setUser(currentUser: HCUser) = with(binding) {
        nameEditText.setText(currentUser.name)
        emailEditText.setText(currentUser.email)
        phoneEditText.setText(currentUser.phone)
        userIdEditText.setText(currentUser.userId)
        companyEditText.setText(currentUser.company)
    }
}
