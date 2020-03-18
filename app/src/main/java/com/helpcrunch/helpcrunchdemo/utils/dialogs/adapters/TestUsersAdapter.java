package com.helpcrunch.helpcrunchdemo.utils.dialogs.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.helpcrunchdemo.utils.dialogs.TestUsersDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class TestUsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<TestUsersDialogFragment.Data> data = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_data_field, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TestUsersDialogFragment.Data item = data.get(position);

        ((Holder) holder).bind(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void swapData(List<TestUsersDialogFragment.Data> data) {
        this.data.clear();
        this.data.addAll(data);
        this.notifyDataSetChanged();
    }

    static class Holder extends RecyclerView.ViewHolder {

        public Holder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(TestUsersDialogFragment.Data item) {
            ((TextView) itemView.findViewById(R.id.key)).setText(item.key);
            ((TextView) itemView.findViewById(R.id.value)).setText(item.value);
        }
    }
}
