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
import org.changken.careapp.datamodels.Doctor;

import java.util.List;

public class ReservationByDoctorAdapter extends ArrayAdapter<AirTableResponse<Doctor>> {
    private List<AirTableResponse<Doctor>> mData;
    private Context mContext;

    public ReservationByDoctorAdapter(@NonNull Context context, @NonNull List<AirTableResponse<Doctor>> data) {
        super(context, 0, data);
        //傳上下文和串列資料
        mData = data;
        mContext = context;
    }

    //每一筆的資料要如何顯示
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AirTableResponse<Doctor> doctor = mData.get(position);

        View listItemView = convertView;

        //如果這一行為null
        if (listItemView == null) {
            //解析一個新的外觀檔進來
            listItemView = LayoutInflater
                    .from(mContext)
                    .inflate(R.layout.doctor_list_cell, parent, false);
        }

        TextView doctorName = (TextView) listItemView.findViewById(R.id.doctor_name);
        TextView doctorSubdivision = (TextView) listItemView.findViewById(R.id.doctor_subdivision);

        doctorName.setText(doctor.getFields().getDoc_name());
        doctorSubdivision.setText(doctor.getFields().getSubDiv_name().get(0));


        return listItemView;
    }

    //更新adapter資料
    public void updateData(List<AirTableResponse<Doctor>> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
