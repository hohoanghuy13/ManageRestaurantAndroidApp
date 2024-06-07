package com.example.managerestaurantapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.managerestaurantapp.R;
import com.example.managerestaurantapp.models.DinningTable;

import java.util.ArrayList;

public class CustomAdapterDinningTable extends ArrayAdapter  {
    Context context;
    int layoutItem;

    ArrayList<DinningTable> listDinningTable= new ArrayList<>();

    public CustomAdapterDinningTable(@NonNull Context context, int resource, @NonNull ArrayList<DinningTable> listDinningTable) {
        super(context, resource, listDinningTable);
        this.context =  context;
        this.layoutItem = resource;
        this.listDinningTable = listDinningTable;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DinningTable dinningTable = listDinningTable.get(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layoutItem,null);
        }

        TextView tvID = (TextView) convertView.findViewById(R.id.tv_TableID);
        String id = String.valueOf(dinningTable.getTableid());
        tvID.setText(id);
        TextView tvseat = (TextView) convertView.findViewById(R.id.tv_Seatable);
        String seat = String.valueOf(dinningTable.getSeat());
        tvseat.setText(seat);
        TextView tvNote= (TextView) convertView.findViewById(R.id.tv_Note);
        tvNote.setText(dinningTable.getNote());

        return convertView;
    }
}