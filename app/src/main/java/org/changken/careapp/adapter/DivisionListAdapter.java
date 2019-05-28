package org.changken.careapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.changken.careapp.R;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.DoctorTime;

import java.util.List;

public class DivisionListAdapter extends RecyclerView.Adapter<DivisionListAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<AirTableResponse<DoctorTime>> mData;
    private DivisionListOnItemClickListener mDivisionListOnItemClickListener = null;

    public DivisionListAdapter(Context context, List<AirTableResponse<DoctorTime>> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.division_list_cell, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        viewHolder.classCell = (TextView) itemView.findViewById(R.id.class_cell);

        itemView.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AirTableResponse<DoctorTime> doctorTimeResponse = mData.get(position);

        holder.classCell.setText(doctorTimeResponse.getFields().getDoc_name().get(0));

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setDivisionListOnItemClickListener(DivisionListOnItemClickListener divisionListOnItemClickListener) {
        this.mDivisionListOnItemClickListener = divisionListOnItemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (mDivisionListOnItemClickListener != null) {
            mDivisionListOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    /**
     * 更新串列資料
     */
    public void updateData(List<AirTableResponse<DoctorTime>> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView classCell;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface DivisionListOnItemClickListener {
        void onItemClick(View view, int position);
    }
}
