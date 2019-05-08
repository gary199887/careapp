package org.changken.careapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.changken.careapp.R;
import org.changken.careapp.models.User;
import org.changken.careapp.tools.Response;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private Context mContext;
    private List<Response<User>> mData;

    public MyListAdapter(Context context, List<Response<User>> data){
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                            .from(mContext)
                            .inflate(R.layout.cell_list, parent, false);

        ViewHolder holder = new ViewHolder(view);

        holder.userId = (TextView) view.findViewById(R.id.user_id);
        holder.userName = (TextView) view.findViewById(R.id.user_name);
        holder.userEmail = (TextView) view.findViewById(R.id.user_email);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Response<User> userResponse = mData.get(position);

        holder.userId.setText(userResponse.getFields().getIdNumber());
        holder.userName.setText(userResponse.getFields().getName());
        holder.userEmail.setText(userResponse.getFields().getEmail());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 更新串列資料
     * */
    public void updateData(List<Response<User>> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView userId;
        public TextView userName;
        public TextView userEmail;

        public ViewHolder(View listView){
            super(listView);
        }
    }
}
