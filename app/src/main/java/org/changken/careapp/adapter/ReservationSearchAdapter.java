package org.changken.careapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.changken.careapp.R;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.Reservation;

import java.util.List;

//收割搂 adapter 類似轉換器，listview 類似滾筒式衛生紙。
public class ReservationSearchAdapter extends ArrayAdapter<AirTableResponse<Reservation>> {
    private List<AirTableResponse<Reservation>> mData;
    private Context mContext;

    public ReservationSearchAdapter(@NonNull Context context, @NonNull List<AirTableResponse<Reservation>> data) {
        super(context, 0, data);
        //傳上下文和串列資料
        mData = data;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AirTableResponse<Reservation> reservation = mData.get(position);

        View listItemView = convertView;
        //如果這一行為null
        if (listItemView == null) {
            //解析一個新的外觀檔進來
            listItemView = LayoutInflater
                    .from(mContext)
                    .inflate(R.layout.reservation_search_cancel_cell, parent, false);
        }

        TextView reservationDivision = (TextView) listItemView.findViewById(R.id.reservationDivision);
        TextView doctorName = (TextView) listItemView.findViewById(R.id.doctorName);
        TextView doctimeDateAndDatepart = (TextView) listItemView.findViewById(R.id.doctimeDateAndDatepart);
        TextView doctimeCurrentNum = (TextView) listItemView.findViewById(R.id.doctimeCurrentNum);
        TextView doctimeMyNum = (TextView) listItemView.findViewById(R.id.doctimeMyNum);
        TextView docRoom = (TextView) listItemView.findViewById(R.id.docRoom);

        //get資料 若傳回來資料型態為list 所以要get(0)第一筆資料
        reservationDivision.setText(  reservation.getFields().getSubDiv_name().get(0));
        doctorName.setText(  reservation.getFields().getDoc_name().get(0));
        doctimeDateAndDatepart.setText(  reservation.getFields().getDocTime_daypartAndDate());
        doctimeCurrentNum.setText( reservation.getFields().getDocTime_currentNum().get(0) + "");
        doctimeMyNum.setText( reservation.getFields().getRes_num() + "");
        docRoom.setText(reservation.getFields().getDocTime_room().get(0));

        //0未報到(GRAY) 1已報到(BLACK) 2過號(RED) 3看診結束(到掛號紀錄 不會在我這顯示!)
        if (reservation.getFields().getRes_status() == 0) {
            reservationDivision.setTextColor(Color.GRAY);
        }
        else if(reservation.getFields().getRes_status() == 2){
            reservationDivision.setTextColor(Color.RED);
        }


        return listItemView;
    }
    public void updateData(List<AirTableResponse<Reservation>> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }
}


