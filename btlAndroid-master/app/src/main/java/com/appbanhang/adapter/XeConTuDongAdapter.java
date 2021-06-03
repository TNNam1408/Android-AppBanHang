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

import com.appbanhang.R;
import com.appbanhang.activity.ChiTietActivity;
import com.appbanhang.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class XeConTuDongAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> arrayxecontudong;

    public XeConTuDongAdapter(Context context, ArrayList<SanPham> arrayxecontudong) {
        this.context = context;
        this.arrayxecontudong = arrayxecontudong;
    }

    @Override
    public int getCount() {
        return arrayxecontudong.size ();
    }

    @Override
    public Object getItem(int position) {
        return arrayxecontudong.get ( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttenxecontudong, txtgiaxecontudong, txtmotaxecontudong;
        public ImageView imgxecontudong;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
     XeConTuDongAdapter.ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new XeConTuDongAdapter.ViewHolder ();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            convertView = inflater.inflate ( R.layout.dong_xecontudong, null );
            viewHolder.txttenxecontudong =(TextView) convertView.findViewById ( R.id.textviewtenxecontudong );
            viewHolder.txtgiaxecontudong =(TextView) convertView.findViewById ( R.id.textviewgiaxecontudong );
            viewHolder.txtmotaxecontudong =(TextView) convertView.findViewById ( R.id.textviewmotaxecontudong );
            viewHolder.imgxecontudong = (ImageView) convertView.findViewById ( R.id.imageviewxecontudong );
            convertView.setTag (viewHolder);

//            convertView.setOnClickListener (new View.OnClickListener () {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent (context, ChiTietActivity.class);
//                    intent.putExtra ( "chitietsanpham", arrayxecontudong.get(position));
//                    context.startActivity (intent);
//                }
//            } );

        }else{
            viewHolder = (XeConTuDongAdapter.ViewHolder) convertView.getTag ();
        }
        SanPham sanPham = (SanPham) getItem ( position );
        viewHolder.txttenxecontudong.setText ( sanPham.getTensanpham ());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgiaxecontudong.setText("Giá :"+decimalFormat.format(sanPham.getGiasanpham())+" VND");
        viewHolder.txtmotaxecontudong.setMaxLines ( 2 );
        viewHolder.txtmotaxecontudong.setEllipsize ( TextUtils.TruncateAt.END );
        viewHolder.txtmotaxecontudong.setText ( sanPham.getMotasanpham () );
        Picasso.get().load(sanPham.getHinhanhsanpham()).centerCrop().resize(100,100).into(viewHolder.imgxecontudong);
        return convertView;
    }
}
