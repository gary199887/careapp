package org.changken.careapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.changken.careapp.R;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.Division;

import java.util.List;

import androidx.annotation.*;

public class DivisionSpinnerAdapter extends ArrayAdapter<AirTableResponse<Division>> {

    private Context mContext;
    private List<AirTableResponse<Division>> mData;

    public DivisionSpinnerAdapter(@NonNull Context context, @NonNull List<AirTableResponse<Division>> data) {
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

    private View getCustomView(int position, View convertView, ViewGroup parent){
        AirTableResponse<Division> itemData = getItem(position);

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater
                    .from(mContext)
                    .inflate(R.layout.spinner_cell, parent, false);
        }

        TextView spinnerOption = (TextView) listItemView.findViewById(R.id.spinner_option);

        spinnerOption.setText(itemData.getFields().getDiv_name());

        return listItemView;
    }

    public void updateData(List<AirTableResponse<Division>> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
