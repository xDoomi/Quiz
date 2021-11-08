package com.example.quiz;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.LeaderViewHolder> {

    private List<User> listusers = new ArrayList<>();

    public void setItems(Collection<User> users){
        listusers.addAll(users);
        notifyDataSetChanged();
    }

    public void clearItems(){
        listusers.clear();
        notifyDataSetChanged();
    }

    @Override
    public LeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leaderitem, parent, false);
        return new LeaderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LeaderViewHolder holder, int position) {
        holder.bind(listusers.get(position));
    }

    @Override
    public int getItemCount() {
        return listusers.size();
    }

    class LeaderViewHolder extends RecyclerView.ViewHolder{
        private TextView userTextView;
        private TextView scoreTextView;

        public LeaderViewHolder(View itemView) {
            super(itemView);
            userTextView = itemView.findViewById(R.id.userName);
            scoreTextView = itemView.findViewById(R.id.userScore);
        }

        public void bind(User user) {
            userTextView.setText(user.getUserName());
            scoreTextView.setText(String.valueOf(user.getUserScore()));
        }
    }
}
