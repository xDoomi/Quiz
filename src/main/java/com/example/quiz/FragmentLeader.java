package com.example.quiz;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FragmentLeader extends Fragment {

    private RecyclerView recyclerLeader;
    private LeaderAdapter leaderAdapter;

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("User");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.recyclerleader, null);

        initRecyclerView(v);
        loadLeader();
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        return v;
    }

    private void initRecyclerView(View v) {
        recyclerLeader = v.findViewById(R.id.leaderView);
        recyclerLeader.setLayoutManager(new LinearLayoutManager(v.getContext()));
        leaderAdapter = new LeaderAdapter();
        recyclerLeader.setAdapter(leaderAdapter);
    }

    private void loadLeader(){
        Handler handler = new Handler();
        Collection<User> users = getUsers();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                leaderAdapter.setItems(users);
                //System.out.println(users);
            }
        }, 700);
    }

    private Collection<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        Query query = myRef.orderByChild("Username");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    users.add(user);
                    //System.out.println(user.getUserName());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("loadQuestion:onCancelled, error: " + error.toException());
            }
        });
        return users;
    }
}
