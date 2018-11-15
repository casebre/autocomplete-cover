package com.cover.technicalassessment.customviews;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cover.technicalassessment.R;

import java.util.List;

public class AutocompleteInsurerAdapter extends ArrayAdapter<String> {

    private Context context;
    private int resource;
    private List<String> insurerList;

    public AutocompleteInsurerAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.insurerList = objects;
    }

    @Override
    public int getCount() {
        return insurerList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try{
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(
                        Activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(resource, parent, false);
            }

            String insurerName = insurerList.get(position);
            TextView textViewItem = convertView.findViewById(R.id.text_view_item);
            textViewItem.setText(insurerName);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
