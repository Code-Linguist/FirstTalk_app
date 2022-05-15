package com.deaf.firsttalk;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Course_part_Adapter extends RecyclerView.Adapter<Course_part_Adapter.ArtistViewHolder>{
    private Context mCtx;
    private List<Course_part_Pojo> artistList1;
    private RecycleItemClickListener clickListener1;

    public Course_part_Adapter(Context mCtx, List<Course_part_Pojo> artistList1) {
        this.mCtx = mCtx;
        this.artistList1 = artistList1;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(mCtx).inflate(R.layout.course_subpart_recycleitem, parent, false);
        return new ArtistViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        final Course_part_Pojo uploadinfo= artistList1.get(position);
        holder.partnm.setText(uploadinfo.getPname());
        //Log.e("ghjh",uploadinfo.getPname());
        //Glide.with(mCtx).load(uploadinfo.getCimage()).into(holder.cimage);
    }


    @Override
    public int getItemCount() {
        return artistList1.size() ;
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView partnm;


        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            partnm = itemView.findViewById(R.id.textpartname);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener1 != null)
                clickListener1.onClick(view, getAdapterPosition());
        }
    }
    public void setClickListener(RecycleItemClickListener itemClickListener) {
        this.clickListener1 = itemClickListener;
    }

}


