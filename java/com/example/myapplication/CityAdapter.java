package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<City> cityList;

    CityAdapter(Context context, List<City> cityList){
        this.cityList = cityList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.nameView.setText(city.getName());
        holder.tempView.setText(city.getTemp());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, tempView;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.TextView1);
            tempView = view.findViewById(R.id.TextView2);
        }
    }
}
