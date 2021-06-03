package com.appbanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbanhang.R;
import com.appbanhang.activity.ChiTietActivity;
import com.appbanhang.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class XeConTayAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> arrayxecontay;

    public XeConTayAdapter(Context context, ArrayList<SanPham> arrayxecontay) {
        this.context = context;
        this.arrayxecontay = arrayxecontay;
    }

    @Override
    public int getCount() {
        return arrayxecontay.size ();
    }

    @Override
    public Object getItem(int position) {
        return arrayxecontay.get ( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttenxecontay, txtgiaxecontay, txtmotaxecontay;
        public ImageView imgxecontay;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        XeConTayAdapter.ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new XeConTayAdapter.ViewHolder ();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            convertView = inflater.inflate ( R.layout.dong_xecontay, null );
            viewHolder.txttenxecontay =(TextView) convertView.findViewById ( R.id.textviewtenxecontay );
            viewHolder.txtgiaxecontay =(TextView) convertView.findViewById ( R.id.textviewgiaxecontay );
            viewHolder.txtmotaxecontay =(TextView) convertView.findViewById ( R.id.textviewmotaxecontay );
            viewHolder.imgxecontay = (ImageView) convertView.findViewById ( R.id.imageviewxecontay );
            convertView.setTag (viewHolder);

        }else{
            viewHolder = (XeConTayAdapter.ViewHolder) convertView.getTag ();
        }
        SanPham sanPham = (SanPham) getItem ( position );
        viewHolder.txttenxecontay.setText ( sanPham.getTensanpham ());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgiaxecontay.setText("Giá :"+decimalFormat.format(sanPham.getGiasanpham())+" VND");
        viewHolder.txtmotaxecontay.setMaxLines ( 2 );
        viewHolder.txtmotaxecontay.setEllipsize ( TextUtils.TruncateAt.END );
        viewHolder.txtmotaxecontay.setText ( sanPham.getMotasanpham () );
        Picasso.get().load(sanPham.getHinhanhsanpham()).centerCrop().resize(100,100).into(viewHolder.imgxecontay);
        return convertView;

    }
}
