package org.changken.careapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.changken.careapp.R;
import org.changken.careapp.datamodels.AirTableDeleteResponse;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.Reservation;
import org.changken.careapp.models.BaseModel;
import org.changken.careapp.models.ModelCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

//收割搂 adapter 類似轉換器，listview 類似滾筒式衛生紙。
public class ReservationSearchAdapter extends ArrayAdapter<AirTableResponse<Reservation>> implements View.OnClickListener {
    private List<AirTableResponse<Reservation>> mData;
    private Context mContext;
    private BaseModel<Reservation> mReservationModel;

    public ReservationSearchAdapter(@NonNull Context context, @NonNull List<AirTableResponse<Reservation>> data, BaseModel<Reservation> reservationModel) {
        super(context, 0, data);
        //傳上下文和串列資料
        mData = data;
        mContext = context;
        mReservationModel = reservationModel;
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

        Button cancel = listItemView.findViewById(R.id.cancel);
        cancel.setTag(position);
        cancel.setOnClickListener(this);

        TextView reservationDivisionData = (TextView) listItemView.findViewById(R.id.reservationDivision_data);
        TextView doctorNameData = (TextView) listItemView.findViewById(R.id.doctorName_data);
        TextView doctimeDateAndDatepartData = (TextView) listItemView.findViewById(R.id.doctimeDateAndDatepart_data);
        TextView doctimeMyNumData = (TextView) listItemView.findViewById(R.id.doctimeMyNum_data);
        TextView doctimeCurrentNumData = (TextView) listItemView.findViewById(R.id.doctimeCurrentNum_data);
        TextView docRoomData = (TextView) listItemView.findViewById(R.id.docRoom_data);

        //get資料 若傳回來資料型態為list 所以要get(0)第一筆資料

        reservationDivisionData.setText(reservation.getFields().getSubDiv_name().get(0));
        doctorNameData.setText(reservation.getFields().getDoc_name().get(0));
        doctimeDateAndDatepartData.setText(reservation.getFields().getDocTime_daypartAndDate());
        doctimeMyNumData.setText(reservation.getFields().getDocTime_currentNum().get(0) + "");
        doctimeCurrentNumData.setText(reservation.getFields().getRes_num() + "");
        docRoomData.setText(reservation.getFields().getDocTime_room().get(0));

        //0未報到(GRAY) 1已報到(BLACK) 2過號(RED) 3看診結束(到掛號紀錄 不會在我這顯示!)
        if (reservation.getFields().getRes_status() == 0) {
            reservationDivisionData.setTextColor(Color.GRAY);
            doctorNameData.setTextColor(Color.GRAY);
            doctimeDateAndDatepartData.setTextColor(Color.GRAY);
            doctimeMyNumData.setTextColor(Color.GRAY);
            doctimeCurrentNumData.setTextColor(Color.GRAY);
            docRoomData.setTextColor(Color.GRAY);
        } else if (reservation.getFields().getRes_status() == 1) {
            reservationDivisionData.setTextColor(Color.BLUE);
            doctorNameData.setTextColor(Color.BLUE);
            doctimeDateAndDatepartData.setTextColor(Color.BLUE);
            doctimeMyNumData.setTextColor(Color.BLUE);
            doctimeCurrentNumData.setTextColor(Color.BLUE);
            docRoomData.setTextColor(Color.BLUE);
        } else if (reservation.getFields().getRes_status() == 2) {
            reservationDivisionData.setTextColor(Color.RED);
            doctorNameData.setTextColor(Color.RED);
            doctimeDateAndDatepartData.setTextColor(Color.RED);
            doctimeMyNumData.setTextColor(Color.RED);
            doctimeCurrentNumData.setTextColor(Color.RED);
            docRoomData.setTextColor(Color.RED);
        }

        return listItemView;
    }

    public void updateData(List<AirTableResponse<Reservation>> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        Toast.makeText(mContext, "按鈕點擊事件 " + mData.get(position).getId(), Toast.LENGTH_SHORT).show();

        mReservationModel.delete(mData.get(position).getId(), new ModelCallback<AirTableDeleteResponse>() {
            @Override
            public void onResponseSuccess(Call<AirTableDeleteResponse> call, Response<AirTableDeleteResponse> response) {
                if (response.body().isDeleted()) {
                    Toast.makeText(mContext, "已刪除成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onResponseFailure(Call<AirTableDeleteResponse> call, Response<AirTableDeleteResponse> response) {
                Toast.makeText(mContext, "刪除失敗!抓不到資料喔!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AirTableDeleteResponse> call, Throwable t) {
                Toast.makeText(mContext, "網路不通!", Toast.LENGTH_SHORT).show();
            }
        });

        mData.remove(position); //or some other task
        notifyDataSetChanged();
    }
}


