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

public class XeSoTuDongAdapter extends BaseAdapter{
    Context context;
    ArrayList<SanPham> arrayxesotudong;

    public XeSoTuDongAdapter(Context context, ArrayList<SanPham> arrayxesotudong) {
        this.context = context;
        this.arrayxesotudong = arrayxesotudong;
    }

    @Override
    public int getCount() {
        return arrayxesotudong.size ();
    }

    @Override
    public Object getItem(int position) {
        return arrayxesotudong.get ( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttenxesotudong, txtgiaxesotudong, txtmotaxesotudong;
        public ImageView imgxesotudong;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder ();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            convertView = inflater.inflate ( R.layout.dong_xesotudong, null );
            viewHolder.txttenxesotudong =(TextView) convertView.findViewById ( R.id.textviewtenxesotudong );
            viewHolder.txtgiaxesotudong =(TextView) convertView.findViewById ( R.id.textviewgiaxesotudong );
            viewHolder.txtmotaxesotudong =(TextView) convertView.findViewById ( R.id.textviewmotaxesotudong );
            viewHolder.imgxesotudong = (ImageView) convertView.findViewById ( R.id.imageviewxesotudong );
            convertView.setTag (viewHolder);
//            convertView.setOnClickListener (new View.OnClickListener () {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent (context, ChiTietActivity.class);
//                    intent.putExtra ( "chitietsanpham", arrayxesotudong.get(position));
//                    context.startActivity (intent);
//                }
//            } );
        }else{
            viewHolder = (ViewHolder) convertView.getTag ();
        }
        SanPham sanPham = (SanPham) getItem ( position );
        viewHolder.txttenxesotudong.setText ( sanPham.getTensanpham ());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgiaxesotudong.setText("Giá :"+decimalFormat.format(sanPham.getGiasanpham())+" VND");
        viewHolder.txtmotaxesotudong.setMaxLines ( 2 );
        viewHolder.txtmotaxesotudong.setEllipsize ( TextUtils.TruncateAt.END );
        viewHolder.txtmotaxesotudong.setText ( sanPham.getMotasanpham () );
        Picasso.get().load(sanPham.getHinhanhsanpham()).centerCrop().resize(100,100).into(viewHolder.imgxesotudong);
        return convertView;
    }
}
