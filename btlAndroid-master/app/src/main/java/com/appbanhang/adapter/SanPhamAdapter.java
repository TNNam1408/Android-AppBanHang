package com.appbanhang.adapter;

import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbanhang.model.SanPham;
import com.appbanhang.R;
import com.appbanhang.activity.ChiTietActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.itemHolder> implements Filterable {
    Context context;
    private ArrayList<SanPham>arrayListSanpham;
    private ArrayList<SanPham>listSanPham;

    public SanPhamAdapter(Context context, ArrayList<SanPham> arrayList,ArrayList<SanPham> arrayList2) {
        this.context = context;
        this.arrayListSanpham = arrayList;
        this.listSanPham = arrayList2;
    }

//    public void setFull(ArrayList<SanPham> arrayList2)
//    {
//        this.listSanPham = arrayList2;
//    }

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
        holder.tvGiasanpham.setText("Giá :"+decimalFormat.format(sanPham.getGiasanpham())+" VND");
        Picasso.get().load(sanPham.getHinhanhsanpham()).centerCrop().resize(150,150).into(holder.imgHinhAnhSanpham);
    }

    @Override
    public int getItemCount() {
        return arrayListSanpham.size();
    }

    private Filter arrayList = new Filter () {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<SanPham>arrayFillte = new ArrayList<> ();

            if(constraint.equals ("") == true || constraint.length() == 0)
            {
                arrayFillte.addAll (listSanPham);
            }else{
                String fillterScanner = constraint.toString ().toLowerCase ().trim ();

                for(SanPham item: listSanPham)
                {
                    if(item.getTensanpham ().toLowerCase ().contains (fillterScanner))
                    {
                        arrayFillte.add (item);
                    }
                }
            }
            FilterResults results = new FilterResults ();
            results.values = arrayFillte;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayListSanpham.clear ();
            arrayListSanpham.addAll (  (ArrayList)results.values);
            notifyDataSetChanged ();
        }
    };

    @Override
    public Filter getFilter() {
        return arrayList;
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
