package com.helpcrunch.demo.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.helpcrunch.demo.R
import com.helpcrunch.demo.databinding.ActivitySendMessageBinding
import com.helpcrunch.library.core.Callback
import com.helpcrunch.library.core.HelpCrunch

class SendMessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySendMessageBinding

    private val hcEventsBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val event: HelpCrunch.Event? =
                intent.getSerializableExtra(HelpCrunch.EVENT_TYPE) as HelpCrunch.Event?
            val data =
                intent.getSerializableExtra(HelpCrunch.EVENT_DATA) as HashMap<String, String>?

            if (event == null) {
                Log.w(HelpCrunch.EVENTS, "Can't receive data")
                return
            }

            if (event == HelpCrunch.Event.MESSAGE_SENDING) {
                if (data != null) {
                    val error = data["error"]
                    val resultData = data["data"]

                    if (error != null) {
                        Log.d("sendMessage", "🔴 $error")
                    } else {
                        addMessage(resultData)
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendMessageBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initViews()
        registerReceiver(hcEventsBroadcastReceiver, IntentFilter(HelpCrunch.EVENTS))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(hcEventsBroadcastReceiver)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        if (supportActionBar != null) {
            supportActionBar!!.setTitle(R.string.send_message)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        binding.sendButton.setOnClickListener { sendMessage() }
    }

    private fun sendMessage() {
        val text = binding.messageText.text.toString()
        if (text.isBlank()) {
            Toast.makeText(this@SendMessageActivity, "Text is empty", Toast.LENGTH_SHORT).show()
            return
        }

        val isForceNewChat = binding.forceNewChat.isChecked
        HelpCrunch.sendMessage(text, isForceNewChat, object : Callback<String?>() {
            override fun onSuccess(result: String?) {
                Log.d("sendMessage", "🟢 $result")
                setSendButtonParameters(View.VISIBLE, View.GONE, true)
            }

            override fun onError(message: String) {
                Log.d("sendMessage", "🔴 $message")
                setSendButtonParameters(View.VISIBLE, View.GONE, true)
            }
        }

        )
        setSendButtonParameters(View.GONE, View.VISIBLE, false)
    }

    private fun addMessage(resultData: String?) {
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_hc_send, null)
        drawable!!.colorFilter = PorterDuffColorFilter(
            ContextCompat.getColor(this, R.color.colorBlue),
            PorterDuff.Mode.SRC_IN
        )
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        val textView = AppCompatTextView(this)
        textView.gravity = Gravity.CENTER_VERTICAL
        textView.setCompoundDrawables(drawable, null, null, null)
        textView.text = resultData
        textView.compoundDrawablePadding = resources.getDimensionPixelSize(R.dimen.messages_padding)
        binding.messages.addView(textView)
    }

    private fun setSendButtonParameters(iconVisible: Int, progressVisible: Int, enabled: Boolean) =
        with(binding) {
            sendButtonIcon.visibility = iconVisible
            sendButtonProgress.visibility = progressVisible
            sendButton.isEnabled = enabled
        }
}