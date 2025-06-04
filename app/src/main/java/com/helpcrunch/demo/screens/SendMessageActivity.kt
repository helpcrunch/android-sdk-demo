package com.helpcrunch.demo.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.helpcrunch.demo.R
import com.helpcrunch.demo.databinding.ActivitySendMessageBinding
import com.helpcrunch.library.core.Callback
import com.helpcrunch.library.core.HelpCrunch

class SendMessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySendMessageBinding

    private val hcEventsBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val event = intent.getSerializableExtra(HelpCrunch.EVENT_TYPE) as HelpCrunch.Event?
            val data =
                intent.getSerializableExtra(HelpCrunch.EVENT_DATA) as HashMap<String, String>?
            binding.sendTextIcon.setLoading(false)
            binding.sendText.isEnabled = true

            if (event == null) {
                Log.w(HelpCrunch.EVENTS, "Can't receive data")
                return
            }

            if (event == HelpCrunch.Event.MESSAGE_SENDING) {
                if (data != null) {
                    val error = data["error"]
                    val resultData = data["data"]

                    if (error != null) {
                        addMessage(buildSpannedString {
                            inSpans(
                                ForegroundColorSpan(
                                    ResourcesCompat.getColor(
                                        resources,
                                        R.color.hc_color_red_error,
                                        theme
                                    )
                                )
                            ) {
                                append(error)
                            }
                        })
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
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(hcEventsBroadcastReceiver, IntentFilter(HelpCrunch.EVENTS))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(hcEventsBroadcastReceiver)
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

        binding.sendText.setOnClickListener { v: View? -> sendMessage() }
    }

    private fun sendMessage() {
        binding.sendTextIcon.setLoading(true)
        binding.sendText.isEnabled = false

        val text = binding.messageText.text.toString()

        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this@SendMessageActivity, "Text is empty", Toast.LENGTH_SHORT).show()
            return
        }
        val isForceNewChat = binding.forceNewChat.isChecked
        HelpCrunch.sendMessage(text, isForceNewChat, object : Callback<String?>() {
            override fun onSuccess(result: String?) {
                Log.d("sendMessage + ", result.orEmpty())
            }

            override fun onError(message: String) {
                Log.d("sendMessage - ", message)
            }
        })
    }

    private fun addMessage(resultData: CharSequence?) {
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
}
