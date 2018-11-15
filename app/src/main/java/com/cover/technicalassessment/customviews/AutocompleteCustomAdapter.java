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
import com.cover.technicalassessment.entities.Address;

import java.util.List;

public class AutocompleteCustomAdapter extends ArrayAdapter<Address> {

    private int resource;

    public AutocompleteCustomAdapter(@NonNull Context context, int resource, @NonNull List<Address> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try{
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(
                        Activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(resource, parent, false);
            }

            Address address = getItem(position);
            TextView textViewItem = convertView.findViewById(R.id.text_view_item);
            textViewItem.setText(address.getDescription());

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
