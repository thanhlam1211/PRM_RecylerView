package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private final IClickUserItemListener recyclerViewInterface;
    private Context mContext;
    private List<User> mListUser;

    public UserAdapter(Context mContext, IClickUserItemListener recyclerViewInterface) {
        this.mContext = mContext;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public void setData(List<User> list){
        this.mListUser = list;
        //important, load and bind data for adapter
        notifyDataSetChanged();
    }

    public void setFilteredList(List<User> filterdList){
        this.mListUser = filterdList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mListUser.get(position);
        if(user==null){
            return;
        }
        holder.imgUser.setImageResource(user.getResource());
        holder.tvName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        if(mListUser !=null){
            return mListUser.size();
        }
        return 0;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgUser;
        private TextView tvName;

        public UserViewHolder(@NonNull View itemView, IClickUserItemListener recyclerViewInterface) {
            super(itemView);

            imgUser = itemView.findViewById(R.id.img_user);
            tvName = itemView.findViewById(R.id.tv_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int position = getBindingAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onClickUserItem(position);
                        }
                    }
                }
            });
        }
    }
}
