package com.example.kaido.chatappwithfirebase.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaido.chatappwithfirebase.Adapter.UserAdapter;
import com.example.kaido.chatappwithfirebase.Model.ContentChat;
import com.example.kaido.chatappwithfirebase.Model.User;
import com.example.kaido.chatappwithfirebase.Notification.Token;
import com.example.kaido.chatappwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> mUser;

    private List<ContentChat> userList;

    FirebaseUser firebaseUser;
    DatabaseReference reference;


    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_chat,container,false);

       recyclerView = view.findViewById(R.id.rv_chat);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

       firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
       userList = new ArrayList<>();

       reference = FirebaseDatabase.getInstance().getReference("ContentChat").child(firebaseUser.getUid());
       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               userList.clear();
               for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                   ContentChat contentChat = snapshot.getValue(ContentChat.class);
                   userList.add(contentChat);
               }
               content();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
       updateToken(FirebaseInstanceId.getInstance().getToken());

       return view;

    }

    private void updateToken(String token) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token1 = new Token(token);
        databaseReference.child(firebaseUser.getUid()).setValue(token1);
    }

    private void content() {
        mUser = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUser.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    for (ContentChat contentChat: userList){
                        if (user.getId().equals(contentChat.getId())){
                            mUser.add(user);
                        }
                    }
                }
                userAdapter = new UserAdapter(getContext(),mUser,true);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
