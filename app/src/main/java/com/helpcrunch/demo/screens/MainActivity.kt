package com.helpcrunch.demo.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.core.view.isVisible
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.helpcrunch.demo.R
import com.helpcrunch.demo.databinding.ActivityMainNewBinding
import com.helpcrunch.demo.design.CustomTheme
import com.helpcrunch.library.core.Callback
import com.helpcrunch.library.core.ERROR_CANT_OPEN_CHAT
import com.helpcrunch.library.core.ERROR_USER_BLOCKED
import com.helpcrunch.library.core.HelpCrunch
import com.helpcrunch.library.core.options.HCOptions
import com.helpcrunch.library.core.options.HCPreChatForm
import com.helpcrunch.library.core.options.theme.HCMessageAreaTheme
import com.helpcrunch.library.core.options.theme.HCTheme
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainNewBinding

    private val hcStateBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val state = intent.getSerializableExtra(HelpCrunch.STATE_TYPE) as HelpCrunch.State?
                ?: HelpCrunch.State.IDLE

            onChatStateChanged(state)
        }
    }

    private val hcEventsBroadcastReceiver: BroadcastReceiver = createHelpCrunchEventsReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        LocalBroadcastManager.getInstance(this).registerReceiver(hcEventsBroadcastReceiver, IntentFilter(HelpCrunch.EVENTS))
        LocalBroadcastManager.getInstance(this).registerReceiver(hcStateBroadcastReceiver, IntentFilter(HelpCrunch.STATE))
    }

    override fun onResume() {
        super.onResume()
        updateUnreadMessages()
        findViewById<View>(R.id.logout_button).isEnabled = HelpCrunch.getUser() != null
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(hcEventsBroadcastReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(hcStateBroadcastReceiver)
    }

    private fun initViews() = with(binding) {
        chatButton.setOnClickListener { handleOpenChatClicked() }
        chatCustomButton.setOnClickListener { openWithCustomSettings() }
        customUserDataButton.setOnClickListener { openCustomUserDataScreen() }
        userDataButton.setOnClickListener { openUserDataScreen() }
        sendMessageButton.setOnClickListener { openSendMessageScreen() }
        userData.setOnClickListener {
            openUserDataScreen()
        }
        logoutButton.setOnClickListener { logout() }
        val versionText = "SDK: v ${HelpCrunch.getVersion()}"
        version.text = versionText
        state.text = getStateString(HelpCrunch.getState())
    }

    private fun getStateString(state: HelpCrunch.State): CharSequence {
        return when (state) {
            HelpCrunch.State.IDLE -> getStateSpannableString("Idle", Color.GRAY)
            HelpCrunch.State.READY -> getStateSpannableString("Ready", Color.parseColor("#4c82f8"))
            HelpCrunch.State.LOADING -> getStateSpannableString("Loading...", Color.LTGRAY)
            HelpCrunch.State.ERROR_BLOCKED_USER -> getStateSpannableString(
                "User is blocked",
                Color.RED
            )
            HelpCrunch.State.ERROR_INITIALIZATION -> getStateSpannableString(
                "Initialization error",
                Color.RED
            )
            HelpCrunch.State.ERROR_UNKNOWN -> getStateSpannableString("Error unknown", Color.RED)
            HelpCrunch.State.HIDDEN -> getStateSpannableString("Hidden", Color.GRAY)
        }
    }

    private fun logout() {
        setLogoutButtonParameters(View.VISIBLE, false)

        HelpCrunch.logout(object : Callback<Any?>() {
            override fun onSuccess(result: Any?) {
                Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                clearBadge()
                setLogoutButtonParameters(View.GONE, false)
            }

            override fun onError(message: String) {
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                setLogoutButtonParameters(View.GONE, true)
            }
        })
    }

    private fun clearBadge() = with(binding) {
        badgeTv.text = null
        badgeView.visibility = View.INVISIBLE
    }

    private fun checkSettingsOpenScreen() {
        var theme = HCTheme.Builder(HCTheme.Type.DEFAULT)
            .build()

        when (binding.themeGroup.checkedRadioButtonId) {
            R.id.light -> theme = HCTheme.Builder(theme = HCTheme.Type.DEFAULT).build()
            R.id.dark -> theme = HCTheme.Builder(theme = HCTheme.Type.DARK).build()
            R.id.custom -> theme = CustomTheme.create(this)
            R.id.random_hex -> theme = HCTheme.Builder(
                mainColor = randomColor(),
                shouldPaintIconsAutomatically = true
            ).build()
        }
        val optionsBuilder = HCOptions.Builder()
            .setTheme(theme)

        // Add "test_url" to your custom fields in the HelpCrunch Dashboard
        // Then you can use it in the pre-chat form
        val preChatForm = HCPreChatForm.Builder()
//            .withField("test_url", "My custom pre-chat URL field", true)
            .build()

        optionsBuilder.setPreChatForm(preChatForm)

        showChat(optionsBuilder.build())
    }

    private fun randomColor(): Int {
        return Color.argb(255, nextInt(256), nextInt(256), nextInt(256))
    }

    private fun openWithCustomSettings() {
        val brandColor = ContextCompat.getColor(this, R.color.send_bg_enable_color)

        val messageAreaTheme = HCMessageAreaTheme.Builder()
            .setButtonType(HCMessageAreaTheme.ButtonType.TEXT)
            .build()

        val theme = HCTheme.Builder(brandColor, true)
            .setMessageAreaTheme(messageAreaTheme)
            .build()

        val preChatForm: HCPreChatForm = HCPreChatForm.Builder()
            .withField("test_url", "My custom pre-chat URL field", true)
            .build()

        val options = HCOptions.Builder()
            .setTheme(theme)
            .setPreChatForm(preChatForm)
            .build()

        showChat(options)
    }

    private fun showChat(options: HCOptions?) {
        setChatScreenButtonParameters(View.GONE, View.VISIBLE, false)

        HelpCrunch.showChatScreen(options, object : Callback<Any?>() {
            override fun onError(message: String) {
                if (message == ERROR_USER_BLOCKED) {
                    val user = HelpCrunch.getUser()
                    if (user != null) {
                        val messageText = """
                            You are a very bad person, ${user.name}.
                            Please, uninstall the application.
                            """.trimIndent()
                        Toast.makeText(this@MainActivity, messageText, Toast.LENGTH_SHORT).show()
                    }
                } else if (message == ERROR_CANT_OPEN_CHAT) {
                    Toast.makeText(
                        this@MainActivity,
                        "Can't open chat. Something is wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                setChatScreenButtonParameters(View.VISIBLE, View.GONE, true)
            }

            override fun onSuccess(result: Any?) {
                setChatScreenButtonParameters(View.VISIBLE, View.GONE, true)
            }
        })
    }

    private fun handleOpenChatClicked() {
        checkSettingsOpenScreen()
        clearBadge()
        HelpCrunch.trackEvent(
            "Event chat opened",
            "https://i.pinimg.com/originals/58/92/e7/5892e7f3cc64c8a912e2494a3ff77e08.jpg",
            "Say Cheese"
        )
    }

    private fun updateUnreadMessages() {
        HelpCrunch.getUnreadChatsCount(object : Callback<Int>() {
            override fun onSuccess(result: Int) {
                setVisibilityForUnreadMessagesBadge(result)
            }

            override fun onError(message: String) {
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setVisibilityForUnreadMessagesBadge(count: Int) = with(binding) {
        badgeView.isVisible = count > 0
        badgeTv.text = count.toString()
    }

    private fun setChatScreenButtonParameters(
        logoVisible: Int,
        progressVisible: Int,
        buttonEnabled: Boolean
    ) = with(binding) {
        progressOpen.visibility = progressVisible
        logoBtn.visibility = logoVisible
        chatButton.isEnabled = buttonEnabled
    }

    private fun setLogoutButtonParameters(visible: Int, enabled: Boolean) = with(binding) {
        progress.visibility = visible
        logoutButton.isEnabled = enabled
    }

    private fun openCustomUserDataScreen() {
        startActivity(Intent(this@MainActivity, CustomUserDataActivity::class.java))
    }

    private fun openUserDataScreen() {
        startActivity(Intent(this@MainActivity, UserDataActivity::class.java))
    }

    private fun openSendMessageScreen() {
        startActivity(Intent(this@MainActivity, SendMessageActivity::class.java))
    }

    private fun getStateSpannableString(state: String, @ColorInt color: Int): CharSequence {
        return buildSpannedString {
            inSpans(ForegroundColorSpan(color)) {
                append(state)
            }
        }
    }

    private fun createHelpCrunchEventsReceiver() = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val event: HelpCrunch.Event? =
                intent.getSerializableExtra(HelpCrunch.EVENT_TYPE) as HelpCrunch.Event?
            val screen: HelpCrunch.Screen? =
                intent.getSerializableExtra(HelpCrunch.SCREEN_TYPE) as HelpCrunch.Screen?
            val data =
                intent.getSerializableExtra(HelpCrunch.EVENT_DATA) as HashMap<String, String>?
            if (event == null) {
                Log.w(HelpCrunch.EVENTS, "Can't receive data")
                return
            }
            when (event) {
                HelpCrunch.Event.FIRST_MESSAGE -> Log.i(HelpCrunch.EVENTS, "First Message")
                HelpCrunch.Event.SCREEN_CLOSED -> if (screen != null) {
                    Log.i(HelpCrunch.EVENTS, "$screen screen: closed")
                } else {
                    Log.w(HelpCrunch.EVENTS, "Can't receive screen event")
                }
                HelpCrunch.Event.SCREEN_OPENED -> if (screen != null) {
                    Log.i(
                        HelpCrunch.EVENTS,
                        screen.toString() + " screen: opened, data: " + (data?.toString() ?: "null")
                    )
                } else {
                    Log.w(HelpCrunch.EVENTS, "Can't receive screen event")
                }
                HelpCrunch.Event.ON_IMAGE_URL,
                HelpCrunch.Event.ON_FILE_URL,
                HelpCrunch.Event.ON_ANY_OTHER_URL -> {
                    Log.i(HelpCrunch.EVENTS, "Url opened. data: $data")
                }
                HelpCrunch.Event.ON_UNREAD_COUNT_CHANGED -> {
                    Log.i(HelpCrunch.EVENTS, "new unread message")
                    val unreadChatsCountStr = data!![HelpCrunch.UNREAD_CHATS]
                    if (unreadChatsCountStr != null) {
                        setVisibilityForUnreadMessagesBadge(unreadChatsCountStr.toInt())
                    }
                }
                HelpCrunch.Event.CHAT_CREATED -> {
                    Log.i(HelpCrunch.EVENTS, "Chat id: " + data!!["chat_id"])
                }
                HelpCrunch.Event.MESSAGE_SENDING -> {
                    Log.i(HelpCrunch.EVENTS, "message sending...")
                }
                HelpCrunch.Event.ON_FIREBASE_NOTIFICATION -> {
                    Log.i(HelpCrunch.EVENTS, "new firebase notification...")
                }
            }
        }
    }

    private fun onChatStateChanged(state: HelpCrunch.State) {
        binding.state.text = getStateString(state)
    }
}
