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

    public static class ViewHolder {
        TextView textViewItem;
    }

    public AutocompleteInsurerAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try{

            ViewHolder viewHolder;
            String insurerName = getItem(position);

            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(
                        Activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(resource, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textViewItem = convertView.findViewById(R.id.text_view_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textViewItem.setText(insurerName);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
