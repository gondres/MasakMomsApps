package com.projectpertama.masakmoms.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projectpertama.masakmoms.R;
import com.projectpertama.masakmoms.detail.DetailActivity;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private List<DataMenu> dataMenus;
    private Context mContext;
    private RecyclerViewClickListener listener;

    public HomeAdapter(List<DataMenu> dataMenus,Context mContext, RecyclerViewClickListener listener){
        this.dataMenus = dataMenus;
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(dataMenus.get(position).getTitle());
        holder.tvDurasi.setText(dataMenus.get(position).getTimes());
        holder.tvPorsi.setText(dataMenus.get(position).getPortion());
        holder.tvDifficulty.setText(dataMenus.get(position).getDifficulty());
        Glide.with(mContext).load(dataMenus.get(position).getImage()).into(holder.imgMenu);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("keyName",dataMenus.get(position).getKey());
                intent.putExtra("title",dataMenus.get(position).getTitle());
                intent.putExtra("image",dataMenus.get(position).getImage());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataMenus.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v,int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.imgMenu)
        ImageView imgMenu;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvDurasi)
        TextView tvDurasi;
        @BindView(R.id.tvPorsi)
        TextView tvPorsi;
        @BindView(R.id.tvDifficulty)
        TextView tvDifficulty;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
}
