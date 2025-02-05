package com.helpcrunch.demo.design

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.helpcrunch.library.R
import com.helpcrunch.library.core.options.theme.HCAvatarTheme
import com.helpcrunch.library.core.options.theme.HCChatAreaTheme
import com.helpcrunch.library.core.options.theme.HCMessageAreaTheme
import com.helpcrunch.library.core.options.theme.HCNotificationsTheme
import com.helpcrunch.library.core.options.theme.HCPreChatTheme
import com.helpcrunch.library.core.options.theme.HCSystemAlertsTheme
import com.helpcrunch.library.core.options.theme.HCTheme
import com.helpcrunch.library.core.options.theme.HCToolbarAreaTheme

class CustomTheme {
    companion object {
        fun create(ctx: Context): HCTheme {
            return HCTheme.build {
                val avatarTheme = HCAvatarTheme.build {
                    setPlaceholderBackgroundColor(ctx.color(R.color.hc_color_chat_bubble_dark_other))
                    setPlaceholderTextColor(ctx.color(R.color.hc_color_white_dark))
                }

                setChatAreaTheme(HCChatAreaTheme.build {
                    setBackgroundColor(ctx.color(R.color.hc_color_chat_dark_bg))
                    setAdditionalMessagesBackgroundColor(ctx.color(R.color.hc_color_chat_bubble_dark_other))
                    setIncomingBubbleColor(ctx.color(R.color.hc_color_chat_bubble_dark_other))
                    setOutcomingBubbleColor(ctx.color(R.color.hc_color_chat_bubble_dark_me))
                    setIncomingBubbleTextColor(ctx.color(R.color.hc_color_white_dark))
                    setOutcomingBubbleTextColor(ctx.color(R.color.hc_color_white_dark))
                    setIncomingBubbleLinkColor(ctx.color(R.color.hc_color_white_dark))
                    setOutcomingBubbleLinkColor(ctx.color(R.color.hc_color_white_dark))
                    setSystemMessageColor(ctx.color(R.color.hc_color_white_dark))
                    setTimeTextColor(ctx.color(R.color.hc_color_white_op40))
                    setProgressViewsColor(ctx.color(R.color.hc_color_white_dark))
                    setFabDownBackgroundColor(ctx.color(R.color.hc_color_toolbar_dark_bg))
                    setBrandingType(HCChatAreaTheme.Branding.DARK)
                    setAvatarTheme(avatarTheme)
                    setIncomingCodeBackgroundColor(ctx.color(R.color.hc_color_code_bg_incoming_dark))
                    setOutcomingCodeBackgroundColor(ctx.color(R.color.hc_color_code_bg_outcoming_dark))
                    setIncomingCodeTextColor(ctx.color(R.color.hc_color_code_color))
                    setOutcomingCodeTextColor(ctx.color(R.color.hc_color_code_color))
                    setIncomingBlockQuoteColor(ctx.color(R.color.hc_color_block_quote_incoming_dark))
                    setOutcomingBlockQuoteColor(ctx.color(R.color.hc_color_block_quote_outcoming_dark))
                    // File
                    setIncomingFileTextColor(ctx.color(R.color.hc_color_white))
                    setOutcomingFileTextColor(ctx.color(R.color.hc_color_white))
                    setOutcomingFileBackgroundColor(ctx.color(R.color.hc_color_code_color))
                    setIncomingFileBackgroundColor(ctx.color(R.color.hc_color_code_color))
                    setOutcomingFileIconColor(ctx.color(R.color.hc_color_code_color))
                    setIncomingFileIconColor(ctx.color(R.color.hc_color_white))
                    setAttachmentIconsColor(ctx.color(R.color.hc_color_chat_bubble_dark_other))
                    setAuthorNameColor(ctx.color(R.color.hc_color_white_dark))
                })

                setMessageAreaTheme(HCMessageAreaTheme.build {
                    setButtonType(HCMessageAreaTheme.ButtonType.TEXT)
                    setInputFieldTextColor(ctx.color(R.color.hc_color_chats_text_dark))
                    setInputFieldTextHintColor(ctx.color(R.color.hc_color_chats_text_hint_dark))
                    setInputOutlineColor(ctx.color(R.color.hc_color_toolbar_dark_bg))
                    setBackgroundColor(ctx.color(R.color.hc_color_message_bar_dark_bg))
                    setMessageMenuBackgroundColor(ctx.color(R.color.hc_color_chat_dark_bg))
                    setMessageMenuSummaryTextColor(ctx.color(R.color.hc_color_menu_summary_color_dark))
                    setMessageMenuIconColor(ctx.color(R.color.hc_color_menu_summary_color_dark))
                    setMessageMenuTextColor(ctx.color(R.color.hc_color_white_dark))
                })

                setToolbarAreaTheme(HCToolbarAreaTheme.build {
                    setBackgroundColor(ctx.color(R.color.hc_color_toolbar_dark_bg))
                    setAgentsTextColor(ctx.color(R.color.hc_color_white_dark))
                    setStatusBarColor(ctx.color(R.color.hc_color_toolbar_dark_bg))
                    setOutlineColor(ctx.color(R.color.hc_color_chat_dark_bg))
                    setAvatarTheme(avatarTheme)
                })

                setSystemAlertsTheme(HCSystemAlertsTheme.build {
                    setToastsBackgroundColor(ctx.color(R.color.hc_color_white_op90))
                    setToastsTextColor(ctx.color(R.color.hc_main_dark))
                    setDialogsHeaderColor(ctx.color(R.color.hc_color_toolbar_dark_bg))
                    setWelcomeMessageBackgroundColor(ctx.color(R.color.hc_color_toolbar_dark_bg))
                    setWelcomeMessageTextColor(ctx.color(R.color.hc_color_white_dark))
                    setWarningDialogsHeaderColor(ctx.color(R.color.hc_color_bg_button_enabled_dark))
                    setDialogAcceptButtonTextColor(ctx.color(R.color.hc_color_white_dark))
                    setDialogCancelButtonTextColor(ctx.color(R.color.hc_color_white_dark))
                    setDialogBackgroundColor(ctx.color(R.color.hc_color_chat_dark_bg))
                    setDialogMessageTextColor(ctx.color(R.color.hc_color_white_dark))
                })

                setPreChatTheme(HCPreChatTheme.build {
                    setButtonContinueBackgroundSelector(R.drawable.btn_hc_rounded_dark)
                    setInputFieldTextColor(ctx.color(R.color.hc_color_chats_text_dark))
                    setInputFieldTextHintColor(ctx.color(R.color.hc_color_chats_text_hint_dark))
                    setInputFieldBackgroundDrawableRes(R.drawable.bg_hc_chat_field_dark)
                    setBackgroundColor(ctx.color(R.color.hc_color_chat_dark_bg))
                    setMessageBackgroundColor(ctx.color(R.color.hc_color_toolbar_dark_bg))
                    setMessageTextColor(ctx.color(R.color.hc_color_white_dark))
                })

                setNotificationsTheme(HCNotificationsTheme.build {
                    setAvatarTheme(avatarTheme)
                    setColor(ctx.color(R.color.hc_color_chat_bubble_dark_other))
                })
            }
        }
    }
}

fun Context.color(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}
