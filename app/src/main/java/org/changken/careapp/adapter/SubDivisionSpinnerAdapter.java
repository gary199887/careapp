package org.changken.careapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.changken.careapp.R;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.SubDivision;

import java.util.List;

public class SubDivisionSpinnerAdapter extends ArrayAdapter<AirTableResponse<SubDivision>> {

    private Context mContext;
    private List<AirTableResponse<SubDivision>> mData;

    public SubDivisionSpinnerAdapter(@NonNull Context context, @NonNull List<AirTableResponse<SubDivision>> data) {
        super(context, 0, data);
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        AirTableResponse<SubDivision> itemData = getItem(position);

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater
                    .from(mContext)
                    .inflate(R.layout.spinner_cell, parent, false);
        }

        TextView spinnerOption = (TextView) listItemView.findViewById(R.id.spinner_option);

        spinnerOption.setText(itemData.getFields().getSubDiv_name());

        return listItemView;
    }

    public void updateData(List<AirTableResponse<SubDivision>> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
