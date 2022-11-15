package com.helpcrunch.demo.design;

import android.graphics.Color;

import androidx.annotation.ColorRes;

import com.helpcrunch.demo.R;
import com.helpcrunch.library.core.options.design.HCAvatarTheme;
import com.helpcrunch.library.core.options.design.HCChatAreaTheme;
import com.helpcrunch.library.core.options.design.HCMessageAreaTheme;
import com.helpcrunch.library.core.options.design.HCNotificationsTheme;
import com.helpcrunch.library.core.options.design.HCPreChatTheme;
import com.helpcrunch.library.core.options.design.HCSystemAlertsTheme;
import com.helpcrunch.library.core.options.design.HCTheme;
import com.helpcrunch.library.core.options.design.HCToolbarAreaTheme;

public class CustomTheme {
    public static HCTheme getTheme() {
        @ColorRes
        int colorWhite = R.color.hc_color_white_dark;

        HCTheme.Builder builder = new HCTheme.Builder(HCTheme.Type.DARK);

        HCAvatarTheme avatarTheme = new HCAvatarTheme.Builder()
                .setPlaceholderBackgroundColor(R.color.hc_color_chat_bubble_dark_other)
                .setPlaceholderTextColor(colorWhite)
                .build();

        // It so happened historically that the theme that is applied to the chat
        // will be applied to the CV and to the chat list.
        // The text color will be matched to the background color and the avatar theme
        // will be copied from the chat avatar theme
        HCChatAreaTheme chatAreaTheme = new HCChatAreaTheme.Builder()
                .setBackgroundColor(R.color.hc_color_chat_dark_bg)
                .setAdditionalMessagesBackgroundColor(R.color.hc_color_chat_bubble_dark_other)
                .setIncomingBubbleColor(R.color.hc_color_chat_bubble_dark_other)
                .setOutcomingBubbleColor(R.color.hc_color_chat_bubble_dark_me)
                .setIncomingBubbleTextColor(colorWhite)
                .setOutcomingBubbleTextColor(colorWhite)
                .setIncomingBubbleLinkColor(colorWhite)
                .setOutcomingBubbleLinkColor(colorWhite)
                .setSystemMessageColor(colorWhite)
                .setTimeTextColor(R.color.hc_color_white_op40)
                .setProgressViewsColor(colorWhite)
                .setFabDownBackgroundColor(R.color.hc_color_toolbar_dark_bg)
                .setBrandingType(HCChatAreaTheme.Branding.DARK)
                .setAvatarTheme(avatarTheme)
                .setIncomingCodeBackgroundColor(R.color.hc_color_code_bg_incoming_dark)
                .setOutcomingCodeBackgroundColor(R.color.hc_color_code_bg_outcoming_dark)
                .setIncomingCodeTextColor(R.color.hc_color_code_color)
                .setOutcomingCodeTextColor(R.color.hc_color_code_color)
                .setIncomingBlockQuoteColor(R.color.hc_color_block_quote_incoming_dark)
                .setOutcomingBlockQuoteColor(R.color.hc_color_block_quote_outcoming_dark)
                // File
                .setIncomingFileTextColor(R.color.hc_color_white)
                .setOutcomingFileTextColor(R.color.hc_color_white)
                .setOutcomingFileBackgroundColor(R.color.hc_color_code_color)
                .setIncomingFileBackgroundColor(R.color.hc_color_code_color)
                .setOutcomingFileIconColor(R.color.hc_color_code_color)
                .setIncomingFileIconColor(R.color.hc_color_white)
                .setAttachmentIconsColor(R.color.hc_color_chat_bubble_dark_other)
                .setAuthorNameColor(colorWhite)
                .build();

        HCMessageAreaTheme messageAreaTheme = new HCMessageAreaTheme.Builder()
                .setButtonType(HCMessageAreaTheme.ButtonType.ICON)
                .setAttachmentsIcon(R.drawable.ic_hc_attach_file)
                .setButtonSendBackgroundSelector(R.drawable.bg_hc_selector_btn_send_dark)
                .setInputFieldTextColor(R.color.hc_color_chats_text_dark)
                .setInputFieldTextHintColor(R.color.hc_color_chats_text_hint_dark)
                .setInputOutlineColor(R.color.hc_color_toolbar_dark_bg)
                .setBackgroundColor(R.color.hc_color_message_bar_dark_bg)
                .setMessageMenuBackgroundColor(R.color.hc_color_chat_dark_bg)
                .setMessageMenuSummaryTextColor(R.color.hc_color_menu_summary_color_dark)
                .setMessageMenuIconColor(R.color.hc_color_menu_summary_color_dark)
                .setMessageMenuTextColor(colorWhite)
                .build();

        HCToolbarAreaTheme toolbarAreaTheme = new HCToolbarAreaTheme.Builder()
                .setBackgroundColor(R.color.hc_color_toolbar_dark_bg)
                .setAgentsTextColor(colorWhite)
                .setStatusBarColor(R.color.hc_color_toolbar_dark_bg)
                .setStatusBarLight(false)
                .setOutlineColor(R.color.hc_color_chat_dark_bg)
                .setBackButtonDrawableRes(R.drawable.ic_hc_arrow_back)
                .setAvatarTheme(avatarTheme)
                .build();

        HCSystemAlertsTheme systemAlertsTheme = new HCSystemAlertsTheme.Builder()
                .setToastsBackgroundColor(R.color.hc_color_white_op90)
                .setToastsTextColor(R.color.hc_main_dark)
                .setDialogsHeaderColor(R.color.hc_color_toolbar_dark_bg)
                .setWelcomeMessageBackgroundColor(R.color.hc_color_toolbar_dark_bg)
                .setWelcomeMessageTextColor(colorWhite)
                .setWarningDialogsHeaderColor(R.color.hc_color_bg_button_enabled_dark)
                .setDialogAcceptButtonTextColor(colorWhite)
                .setDialogCancelButtonTextColor(colorWhite)
                .setDialogBackgroundColor(R.color.hc_color_chat_dark_bg)
                .setDialogMessageTextColor(colorWhite)
                .build();

        HCPreChatTheme preChatTheme = new HCPreChatTheme.Builder()
                .setButtonContinueBackgroundSelector(R.drawable.btn_hc_rounded_dark)
                .setInputFieldTextColor(R.color.hc_color_chats_text_dark)
                .setInputFieldTextHintColor(R.color.hc_color_chats_text_hint_dark)
                .setInputFieldBackgroundDrawableRes(R.drawable.bg_hc_chat_field_dark)
                .setBackgroundColor(R.color.hc_color_chat_dark_bg)
                .setMessageBackgroundColor(R.color.hc_color_toolbar_dark_bg)
                .setMessageTextColor(colorWhite)
                .build();

        HCNotificationsTheme notificationsTheme = new HCNotificationsTheme.Builder()
                .setAvatarTheme(avatarTheme)
                .setColor(Color.parseColor("#4D4D7F")) // (sic!)
                .build();

        builder.setChatAreaTheme(chatAreaTheme);
        builder.setMessageAreaTheme(messageAreaTheme);
        builder.setToolbarAreaTheme(toolbarAreaTheme);
        builder.setSystemAlertsTheme(systemAlertsTheme);
        builder.setPreChatTheme(preChatTheme);
        builder.setNotificationsTheme(notificationsTheme);

        return builder.build();
    }
}
