package com.helpcrunch.helpcrunchdemo.design;

import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.library.options.design.HCAvatarTheme;
import com.helpcrunch.library.options.design.HCChatAreaTheme;
import com.helpcrunch.library.options.design.HCMessageAreaTheme;
import com.helpcrunch.library.options.design.HCNotificationsTheme;
import com.helpcrunch.library.options.design.HCPreChatTheme;
import com.helpcrunch.library.options.design.HCSystemAlertsTheme;
import com.helpcrunch.library.options.design.HCTheme;
import com.helpcrunch.library.options.design.HCToolbarAreaTheme;

public class CustomTheme {
    public static HCTheme getDesign() {
        HCAvatarTheme avatarTheme = new HCAvatarTheme.Builder()
                .setPlaceholderBackgroundColor(R.color.avatar_placeholder_color)
                .setPlaceholderTextColor(android.R.color.white)
                .build();

        HCPreChatTheme preChatTheme = new HCPreChatTheme.Builder()
                .setButtonContinueBackgroundSelector(R.drawable.bg_btn_accept)
                .setInputFieldTextColor(R.color.hc_color_chats_text_dark)
                .setInputFieldTextHintColor(R.color.hc_color_chats_text_hint_dark)
                .setInputFieldBackgroundDrawableRes(R.drawable.bg_chat_field)
                .setBackgroundColor(R.color.background_color)
                .setMessageBackgroundColor(R.color.welcome_messages_background_color)
                .setMessageTextColor(android.R.color.white)
                .build();

        HCChatAreaTheme chatAreaTheme = new HCChatAreaTheme.Builder()
                .setIncomingBubbleTextColor(android.R.color.white)
                .setOutcomingBubbleTextColor(android.R.color.white)
                .setIncomingBubbleLinkColor(android.R.color.white)
                .setOutcomingBubbleLinkColor(android.R.color.white)
                .setIncomingBubbleColor(R.color.incoming_bubble_color)
                .setOutcomingBubbleColor(R.color.outoming_bubble_color)
                .setIncomingCodeBackgroundColor(R.color.hc_color_code_bg_incoming_dark)
                .setOutcomingCodeBackgroundColor(R.color.hc_color_code_bg_outcoming_dark)
                .setIncomingCodeTextColor(R.color.hc_color_code_color)
                .setOutcomingCodeTextColor(R.color.hc_color_code_color)
                .setIncomingBlockQuoteColor(R.color.hc_color_block_quote_incoming_dark)
                .setOutcomingBlockQuoteColor(R.color.hc_color_block_quote_outcoming_dark)
                .setIncomingFileTextColor(R.color.hc_color_chats_text)
                .setOutcomingFileTextColor(R.color.hc_color_chats_text)
                .setIncomingFileIconBackgroundColor(android.R.color.white)
                .setOutcomingFileIconBackgroundColor(android.R.color.white)
                .setAuthorNameColor(android.R.color.white)
                .setSystemMessageColor(android.R.color.white)
                .setTimeTextColor(android.R.color.white)
                .setProgressViewsColor(android.R.color.white)
                .setBackgroundColor(R.color.background_color)
                .setFabDownBackgroundRes(R.drawable.bg_bottom_fab)
                .setFabIconRes(R.drawable.ic_arrow_downward)
                .setBrandingType(HCChatAreaTheme.Branding.DARK)
                .setAvatarTheme(avatarTheme)
                .build();

        HCMessageAreaTheme messageAreaTheme = new HCMessageAreaTheme.Builder()
                .setButtonType(HCMessageAreaTheme.ButtonType.TEXT)
                .setButtonTextColor(android.R.color.holo_red_dark)
                .setAttachmentsIcon(R.drawable.ic_attach_file)
                .setButtonSendBackgroundSelector(R.drawable.button_send)
                .setButtonIcon(R.drawable.ic_arrow_upward)
                .setBackgroundColor(R.color.background_color)
                .setInputFieldTextColor(android.R.color.white)
                .setInputFieldTextHintColor(R.color.hc_color_chats_text_hint_dark)
                .setInputFieldBackgroundDrawableRes(R.drawable.bg_chat_field)
                .setMessageMenuBackgroundColor(R.color.statusbar_color)
                .setMessageMenuSummaryTextColor(android.R.color.white)
                .setMessageMenuIconColor(R.color.toolbar_icon_color)
                .setMessageMenuTextColor(android.R.color.black)
                .build();

        HCSystemAlertsTheme systemAlerts = new HCSystemAlertsTheme.Builder()
                .setDialogsHeaderColor(R.color.statusbar_color)
                .setToastsBackgroundDrawableRes(R.drawable.hc_toast_default)
                .setToastsTextColor(R.color.system_snack_text_color)
                .setWelcomeMessageBackgroundColor(R.color.welcome_messages_background_color)
                .setWelcomeMessageTextColor(android.R.color.white)
                .setWarningDialogsHeaderColor(R.color.hc_color_bg_button_enabled_dark)
                .setDialogAcceptButtonDrawableRes(R.drawable.bg_btn_accept)
                .setDialogCancelButtonDrawableRes(R.drawable.bg_btn_cancel)
                .build();

        HCToolbarAreaTheme toolbarAreaTheme = new HCToolbarAreaTheme.Builder()
                .setBackgroundColor(R.color.toolbar_color)
                .setStatusBarColor(R.color.statusbar_color)
                .setStatusBarLight(false)
                .setOutlineColor(R.color.toolbar_outline_color)
                .setAgentsTextColor(android.R.color.white)
                .setBackButtonDrawableRes(R.drawable.ic_keyboard_arrow_left)
                .build();

        HCNotificationsTheme notificationsDesign = new HCNotificationsTheme.Builder()
                .setChannelTitle("Support")
                .setMessagesCounterEnabled(false)
                .setSmallIconRes(R.drawable.ic_favicon)
                .setAvatarTheme(avatarTheme)
                .build();

        return new HCTheme.Builder()
                .setToolbarAreaTheme(toolbarAreaTheme)
                .setMessageAreaTheme(messageAreaTheme)
                .setChatAreaTheme(chatAreaTheme)
                .setSystemAlertsTheme(systemAlerts)
                .setNotificationsTheme(notificationsDesign)
                .setPreChatTheme(preChatTheme)
                .build();
    }
}
