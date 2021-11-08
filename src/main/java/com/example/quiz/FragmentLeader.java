package com.example.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.Collection;

public class FragmentLeader extends Fragment {

    private RecyclerView recyclerLeader;
    private LeaderAdapter leaderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.recyclerleader, null);

        initRecyclerView(v);
        loadLeader();

        return v;
    }

    private void initRecyclerView(View v) {
        recyclerLeader = v.findViewById(R.id.leaderView);
        recyclerLeader.setLayoutManager(new LinearLayoutManager(v.getContext()));
        leaderAdapter = new LeaderAdapter();
        recyclerLeader.setAdapter(leaderAdapter);
    }

    private void loadLeader(){
        Collection<User> users = getUsers();
        leaderAdapter.setItems(users);
    }

    private Collection<User> getUsers(){
        return Arrays.asList(
                new User("Vlad", 100),
                new User("Pasha", 20),
                new User("Alina", 10)
        );
    }
}
