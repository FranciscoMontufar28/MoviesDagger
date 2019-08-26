package com.example.moviesfeed.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesfeed.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.viewHolder> {

    public List<ViewModel> data;

    public ListAdapter(List<ViewModel> list){
        this.data = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.country.setText(data.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text_view_fragment_title)
        public TextView title;
        @BindView(R.id.text_view_fragment_country)
        public TextView country;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
