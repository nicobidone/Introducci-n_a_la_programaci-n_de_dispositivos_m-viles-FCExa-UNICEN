package com.unicen.tandilrecicla.ui.notifications;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.unicen.tandilrecicla.R;

public class NotificationsRecyclerViewAdapter extends RecyclerView.Adapter<NotificationsRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "NotificationsRecyclerVA";

    private String[] mPoints;

    NotificationsRecyclerViewAdapter(String[] mPoints) {
        this.mPoints = mPoints;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_notifications_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");

        holder.textDescription.setText(mPoints[position]);
    }

    @Override
    public int getItemCount() {
        return mPoints.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageDescription;
        TextView textDescription;
        ConstraintLayout constraintLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textDescription = itemView.findViewById(R.id.layout_list_notifications_item_text);
            imageDescription = itemView.findViewById(R.id.layout_list_notifications_item_image);
            constraintLayout = itemView.findViewById(R.id.layout_list_notifications_item_parent);
        }
    }
}
