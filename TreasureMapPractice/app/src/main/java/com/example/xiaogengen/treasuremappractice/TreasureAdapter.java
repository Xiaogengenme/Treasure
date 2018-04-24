package com.example.xiaogengen.treasuremappractice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TreasureAdapter extends ArrayAdapter<Treasure> {
    private int resourceId;
    public TreasureAdapter(Context context, int textViewResourceId, List<Treasure> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Treasure treasure=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView treasureImage=(ImageView)view.findViewById(R.id.treasure_image);
        TextView treasureName=(TextView)view.findViewById(R.id.treasure_name);
        //treasureImage.setImageResource(treasure.getImageId());
        treasureName.setText(treasure.getTreasureName()+"   "+treasure.getValueTreasure()+"分");
        return view;
    }
}

