package com.salekin.pwassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    JSONArray jsonArray;
    Context context;

    public Adapter(JSONArray jsonArray, Context context) {
        this.jsonArray = jsonArray;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject reader = jsonArray.getJSONObject(position);
            holder.name.setText(reader.getString("name"));
            Glide.with(context)
                    .load(reader.getString("profileImage"))
                    .into(holder.profile);
           JSONArray sub = reader.getJSONArray("subjects");
//           System.out.println(sub.toString());
           for (int i=0;i<sub.length();i++) {
               holder.subjects.setText(holder.subjects.getText().toString()+" "+sub.getString(i).toString());
           }

            JSONArray qlf = reader.getJSONArray("qualification");
//            System.out.println(qlf.toString());
            for (int i=0;i<qlf.length();i++) {
                holder.qualifications.setText(holder.qualifications.getText().toString()+" "+qlf.getString(i).toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,subjects,qualifications;
        ImageView profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            subjects = itemView.findViewById(R.id.subjects);
            qualifications = itemView.findViewById(R.id.qualifications);
            profile = itemView.findViewById(R.id.profileImage);
        }
    }
}
