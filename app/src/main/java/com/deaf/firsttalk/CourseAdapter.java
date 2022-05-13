package com.deaf.firsttalk;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ArtistViewHolder>{
    private Context mCtx;
    private List<CourseImgPojo> artistList1;
    private RecycleItemClickListener clickListener1;

    public CourseAdapter(Context mCtx, List<CourseImgPojo> artistList1) {
        this.mCtx = mCtx;
        this.artistList1 = artistList1;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(mCtx).inflate(R.layout.course_recycleitem, parent, false);
        return new ArtistViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        final CourseImgPojo uploadinfo= artistList1.get(position);
        holder.cname.setText(uploadinfo.cname);
        holder.cuniversirt.setText(uploadinfo.cuniversity);
         //Log.e("helo", String.valueOf(uploadinfo.cimage));
       // Log.e("hello", String.valueOf(uploadinfo.week));
        Glide.with(mCtx).load(uploadinfo.getCimage()).into(holder.cimage);
    }


    @Override
    public int getItemCount() {
        return artistList1.size() ;
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView cname, cuniversirt;
        ImageView cimage;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            cname = itemView.findViewById(R.id.cnametextView);
            cuniversirt = itemView.findViewById(R.id.cuniversity);
            cimage= itemView.findViewById(R.id.cimageview);
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


