package com.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import com.appbanhang.R;
import com.appbanhang.model.LoaiSanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class LoaiSanPhamAdapter extends  BaseAdapter{
    ArrayList<LoaiSanPham> loaiSanPhamArrayList;
    Context context;

    public LoaiSanPhamAdapter(ArrayList<LoaiSanPham> loaiSanPhamArrayList, Context context) {
        this.loaiSanPhamArrayList = loaiSanPhamArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return loaiSanPhamArrayList.size ();
    }

    @Override
    public Object getItem(int position) {
        return loaiSanPhamArrayList.get ( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class viewHolder{
        TextView txttenloaisanpham;
        ImageView imageloaisanpham;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = null;
        if(convertView ==null){
            viewHolder = new viewHolder ();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            convertView = inflater.inflate ( R.layout.dong_loaisanpham, null );
            viewHolder.txttenloaisanpham= convertView.findViewById ( R.id.tenloaisanpham );
            viewHolder.imageloaisanpham = convertView.findViewById (R.id.imageloaisanpham);
            convertView.setTag ( viewHolder );
        }else{
            viewHolder = (viewHolder) convertView.getTag ();
        }
        LoaiSanPham loaiSanPham = (LoaiSanPham) getItem ( position );
        viewHolder.txttenloaisanpham.setText ( loaiSanPham.getTenloaisanpham ());
        Picasso.get().load(loaiSanPham.getHinhanhloaisanpham ()).centerCrop().resize(150,150).into(viewHolder.imageloaisanpham);

        return convertView;
    }
}