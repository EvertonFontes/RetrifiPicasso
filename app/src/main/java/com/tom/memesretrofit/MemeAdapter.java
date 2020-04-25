package com.tom.memesretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.ViewHolder>{
    private List<Memes> listM;
    private Context context;

    public MemeAdapter(List<Memes> listM,Context context) {
        this.listM = listM;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_memes,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Memes memes = listM.get(position);
        holder.name_meme.setText(memes.getTitle());

        Picasso.get().load(memes.getThumbnailUrl()).placeholder(R.drawable.meme).into(holder.image_meme);
        /*
            Glide.with(holder.image_meme.getContext())
                    .load(memes.getThumbnailUrl())
                    .centerCrop()
                    .placeholder(R.drawable.meme)
                    .into(holder.image_meme);
        */
        //Glide.with(holder.image_meme.getContext()).load(memes.getThumbnailUrl()).into(holder.image_meme);
    }

    @Override
    public int getItemCount() {
        return listM.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_meme;
        TextView name_meme;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_meme = itemView.findViewById(R.id.image_meme);
            name_meme = itemView.findViewById(R.id.name_meme);

        }
    }
}
