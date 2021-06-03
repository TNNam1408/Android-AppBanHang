package com.appbanhang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbanhang.R;
import com.appbanhang.model.DonHang;

import java.util.ArrayList;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.itemHolder> {
    Context context;
    ArrayList<DonHang> donHangArrayList;

    public DonHangAdapter(Context context, ArrayList<DonHang> donHangArrayList) {
        this.context = context;
        this.donHangArrayList = donHangArrayList;
    }

    @Override
    public itemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.dong_qldonhang ,null);
        itemHolder itemHolder = new itemHolder ( view );
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(itemHolder holder, int position) {
        if(holder !=null){
            holder.id.setText ( donHangArrayList.get ( position ).getIddh () );
            holder.tenkhach.setText ( donHangArrayList.get ( position ).getTenkhach () );
            holder.sdt.setText ( donHangArrayList.get ( position ).getSdt () );
            holder.email.setText ( donHangArrayList.get ( position ).getEmail () );
        }
    }

    @Override
    public int getItemCount() {
        return donHangArrayList.size ();
    }

    public class itemHolder extends RecyclerView.ViewHolder{
        public TextView tenkhach;
        public TextView sdt;
        public  TextView email;
        public TextView id;
        public itemHolder(View itemView) {
            super ( itemView );
            id = itemView.findViewById ( R.id.tv_idkhach );
            tenkhach = itemView.findViewById ( R.id.tv_tenkhach );
            sdt = itemView.findViewById ( R.id.tv_sdt );
            email = itemView.findViewById ( R.id.tv_email );
        }
    }
}
