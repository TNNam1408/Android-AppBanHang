package com.appbanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbanhang.model.SanPham;
import com.appbanhang.R;
import com.appbanhang.activity.ChiTietActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.itemHolder>{
    Context context;
    ArrayList<SanPham>arrayListSanpham;

    public SanPhamAdapter(Context context, ArrayList<SanPham> arrayListSanpham) {
        this.context = context;
        this.arrayListSanpham = arrayListSanpham;
    }

    @Override
    public itemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat,null);
        itemHolder itemHolder=new itemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(itemHolder holder, int position) {
        SanPham sanPham=new SanPham();
        sanPham=arrayListSanpham.get(position);
        holder.tvTensanpham.setText(sanPham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.tvGiasanpham.setText("Giá :"+decimalFormat.format(sanPham.getGiasanpham())+" Đ");
        Picasso.get().load(sanPham.getHinhanhsanpham()).centerCrop().resize(150,150).into(holder.imgHinhAnhSanpham);
    }

    @Override
    public int getItemCount() {
        return arrayListSanpham.size();
    }

    public class itemHolder extends RecyclerView.ViewHolder{
        public ImageView imgHinhAnhSanpham;
        public TextView tvTensanpham;
        public TextView tvGiasanpham;

        public itemHolder(View itemView) {
            super(itemView);
            imgHinhAnhSanpham=itemView.findViewById(R.id.img_sanpham);
            tvTensanpham=itemView.findViewById(R.id.tv_tensanpham);
            tvGiasanpham=itemView.findViewById(R.id.tv_giasanpham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, ChiTietActivity.class);
                    intent.putExtra("chitietsanpham",arrayListSanpham.get(getPosition()));
                    Toast.makeText(context, arrayListSanpham.get(getPosition()).getTensanpham(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }
    }
}
