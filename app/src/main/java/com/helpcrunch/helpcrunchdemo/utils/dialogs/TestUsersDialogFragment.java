package com.helpcrunch.helpcrunchdemo.utils.dialogs;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.helpcrunchdemo.utils.dialogs.adapters.TestUsersAdapter;
import com.helpcrunch.library.core.models.user.HCUser;

import java.util.ArrayList;

public class TestUsersDialogFragment extends DialogFragment {
    private ArrayList<Data> user = new ArrayList<>();

    public TestUsersDialogFragment(HCUser user) {
        this.user.clear();
        this.user.add(new Data("Name", user.getName()));
        this.user.add(new Data("Email", user.getEmail()));
        this.user.add(new Data("Phone", user.getPhone()));
        this.user.add(new Data("Company", user.getCompany()));
        String id = user.getUserId() == null || TextUtils.isEmpty(user.getUserId()) ? "N/A" : user.getUserId();
        this.user.add(new Data("User Id", id));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_users_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView list = view.findViewById(R.id.list);

        TestUsersAdapter adapter = new TestUsersAdapter();
        adapter.swapData(user);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager((this.getContext())));
    }


    public static class Data {
        public String key;
        public String value;

        public Data(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
