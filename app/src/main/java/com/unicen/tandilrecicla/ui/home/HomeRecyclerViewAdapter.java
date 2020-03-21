package com.unicen.tandilrecicla.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unicen.tandilrecicla.R;
import com.unicen.tandilrecicla.data.model.Recycling;
import com.unicen.tandilrecicla.data.model.Utils;

import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "HomeRecyclerViewAdapter";

    private List<Recycling> recyclingList;

    HomeRecyclerViewAdapter(List<Recycling> recyclingList) {
        this.recyclingList = recyclingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_home_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        final String[] quantities = {
                String.valueOf(recyclingList.get(position).getBottles()),
                String.valueOf(recyclingList.get(position).getPaperboard()),
                String.valueOf(recyclingList.get(position).getGlass()),
                String.valueOf(recyclingList.get(position).getTetrabriks()),
                String.valueOf(recyclingList.get(position).getCans())
        };
        final String[] descriptions = Utils.getRecyclingNames();
        int[] vordiplomColors = Utils.getVordiplomColors();

        holder.textDate.setText(recyclingList.get(position).getDate());

        holder.textDescriptionBottles.setText(descriptions[0]);
        holder.textDescriptionPaperboard.setText(descriptions[1]);
        holder.textDescriptionGlass.setText(descriptions[2]);
        holder.textDescriptionTetrabriks.setText(descriptions[3]);
        holder.textDescriptionCans.setText(descriptions[4]);

        holder.textQuantityBottles.setText(quantities[0]);
        holder.textQuantityPaperboard.setText(quantities[1]);
        holder.textQuantityGlass.setText(quantities[2]);
        holder.textQuantityTetrabriks.setText(quantities[3]);
        holder.textQuantityCans.setText(quantities[4]);

        holder.textDescriptionBottles.setBackgroundColor(vordiplomColors[0]);
        holder.textQuantityBottles.setBackgroundColor(vordiplomColors[0]);
        holder.textDescriptionPaperboard.setBackgroundColor(vordiplomColors[1]);
        holder.textQuantityPaperboard.setBackgroundColor(vordiplomColors[1]);
        holder.textDescriptionGlass.setBackgroundColor(vordiplomColors[2]);
        holder.textQuantityGlass.setBackgroundColor(vordiplomColors[2]);
        holder.textDescriptionTetrabriks.setBackgroundColor(vordiplomColors[3]);
        holder.textQuantityTetrabriks.setBackgroundColor(vordiplomColors[3]);
        holder.textDescriptionCans.setBackgroundColor(vordiplomColors[4]);
        holder.textQuantityCans.setBackgroundColor(vordiplomColors[4]);
    }

    @Override
    public int getItemCount() {
        return recyclingList != null ? recyclingList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textDescriptionBottles;
        TextView textQuantityBottles;
        TextView textDescriptionPaperboard;
        TextView textQuantityPaperboard;
        TextView textDescriptionGlass;
        TextView textQuantityGlass;
        TextView textDescriptionTetrabriks;
        TextView textQuantityTetrabriks;
        TextView textDescriptionCans;
        TextView textQuantityCans;
        TextView textDate;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.layout_list_item_home_date);
            textDescriptionBottles = itemView.findViewById(R.id.layout_list_item_home_text_bottles_description);
            textQuantityBottles = itemView.findViewById(R.id.layout_list_item_home_text_bottles_quantity);
            textDescriptionPaperboard = itemView.findViewById(R.id.layout_list_item_home_text_paperboard_description);
            textQuantityPaperboard = itemView.findViewById(R.id.layout_list_item_home_text_paperboard_quantity);
            textDescriptionGlass = itemView.findViewById(R.id.layout_list_item_home_text_glass_description);
            textQuantityGlass = itemView.findViewById(R.id.layout_list_item_home_text_glass_quantity);
            textDescriptionTetrabriks = itemView.findViewById(R.id.layout_list_item_home_text_tetrabriks_description);
            textQuantityTetrabriks = itemView.findViewById(R.id.layout_list_item_home_text_tetrabriks_quantity);
            textDescriptionCans = itemView.findViewById(R.id.layout_list_item_home_text_cans_description);
            textQuantityCans = itemView.findViewById(R.id.layout_list_item_home_text_cans_quantity);
        }
    }
}
