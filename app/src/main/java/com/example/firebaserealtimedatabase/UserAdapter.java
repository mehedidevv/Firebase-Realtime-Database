package com.example.firebaserealtimedatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    public UserAdapter(List<UserClass> userClassList) {
        this.userClassList = userClassList;
    }

    List<UserClass>userClassList;



    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Connection of Model Design
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_design_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        UserClass userClass=userClassList.get(position);
        holder.usernameTV.setText("Name:"+userClass.getName());
        holder.userphoneTV.setText("Phone No:"+userClass.getPhone());
        holder.userAgeTV.setText("Age:"+userClass.getAge());

    }

    @Override
    public int getItemCount() {
        return userClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTV,userphoneTV,userAgeTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            usernameTV=itemView.findViewById(R.id.userNameTV);
            userphoneTV=itemView.findViewById(R.id.userPhoneTV);
            userAgeTV=itemView.findViewById(R.id.userAgeTV);
        }
    }
}
