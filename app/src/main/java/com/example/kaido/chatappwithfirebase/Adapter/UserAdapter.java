package com.example.kaido.chatappwithfirebase.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kaido.chatappwithfirebase.Activity.MessageActivity;
import com.example.kaido.chatappwithfirebase.Model.Chat;
import com.example.kaido.chatappwithfirebase.Model.User;
import com.example.kaido.chatappwithfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private Boolean isActive;
    private List<User> mUsers;
    String last_message;

    public UserAdapter(Context mContext, List<User> mUsers, Boolean isActive) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        this.isActive = isActive;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
       return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = mUsers.get(position);
        holder.tvUsername.setText(user.getUsername());
        if (user.getImageURL().equals("default")){
            holder.imageProfile.setImageResource(R.mipmap.ic_launcher);
        }else {
            Glide.with(mContext).load(user.getImageURL()).into(holder.imageProfile);
        }

        if (isActive){
            lastMessage(user.getId(),holder.last_mess);
        }else {
            holder.last_mess.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("id",user.getId());
                mContext.startActivity(intent);
            }
        });
        if (isActive){
            if (user.getStatus().equals("Online")){
                holder.stt_on.setVisibility(View.VISIBLE);
                holder.stt_off.setVisibility(View.GONE);
            }
            else {
                holder.stt_on.setVisibility(View.GONE);
                holder.stt_off.setVisibility(View.VISIBLE);
            }

        }else {
            holder.stt_on.setVisibility(View.GONE);
            holder.stt_off.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUsername;
        ImageView imageProfile;
        ImageView stt_on;
        ImageView stt_off;
        TextView last_mess;
        public ViewHolder(View view) {
            super(view);

            tvUsername = view.findViewById(R.id.tvUsername);
            imageProfile = view.findViewById(R.id.profileImage);
            stt_on = view.findViewById(R.id.stt_on);
            stt_off = view.findViewById(R.id.stt_off);
            last_mess = view.findViewById(R.id.tvLastMess);
        }
    }
    private void lastMessage(final String userid, final TextView last_mess){

        last_message = "default";
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference  = FirebaseDatabase.getInstance().getReference("Chats");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    assert chat != null;
                    assert firebaseUser != null;
                    if (chat.getReceiver().equals(firebaseUser.getUid()) && chat.getSender().equals(userid) ||
                    chat.getReceiver().equals(userid) && chat.getSender().equals(firebaseUser.getUid())){
                        last_message = chat.getMessage();
                    }
                }
                switch (last_message){
                    case "default":
                        last_mess.setText("No message");
                        break;
                    default:
                        last_mess.setText(last_message);
                }
                last_message = "default";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
