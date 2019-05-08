package com.helpcrunch.helpcrunchdemo.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.library.ui.HelpCrunchChatExtraKeys;
import com.helpcrunch.library.ui.HelpCrunchChatFragment;
import com.helpcrunch.library.ui.design.HelpCrunchDesign;

public class PlainChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_plain_chat);

        Bundle bundle = new Bundle();
        bundle.putBoolean(HelpCrunchChatExtraKeys.REQUEST_NAME, false);
        bundle.putInt(HelpCrunchChatExtraKeys.DESIGN, HelpCrunchDesign.MODERN);
        bundle.putBoolean(HelpCrunchChatExtraKeys.TOOLBAR_VISIBILITY, false);

        HelpCrunchChatFragment fragment = HelpCrunchChatFragment.newInstance(getIntent().getExtras());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
