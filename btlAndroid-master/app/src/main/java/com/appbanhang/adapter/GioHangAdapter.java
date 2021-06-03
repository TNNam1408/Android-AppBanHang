package com.appbanhang.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.appbanhang.R;
import com.appbanhang.activity.GioHangActivity;
import com.appbanhang.activity.MainActivity;
import com.appbanhang.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.appbanhang.activity.GioHangActivity.tvThongbao;


public class GioHangAdapter extends BaseAdapter{
    Context context;
    ArrayList<GioHang>arryGioHang;

    public GioHangAdapter(Context context, ArrayList<GioHang> arryGioHang) {
        this.context = context;
        this.arryGioHang = arryGioHang;
    }

    @Override
    public int getCount() {
        return arryGioHang.size();
    }

    @Override
    public Object getItem(int i) {
        return arryGioHang.get ( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view=layoutInflater.inflate(R.layout.dong_giohang,null);
        final TextView tvtengiohang,tvgiagiohang;
        ImageView imggiohang;
        final Button btnminus,btnvalues,btnplus;

        tvtengiohang=view.findViewById(R.id.tv_tengiohang);
        tvgiagiohang=view.findViewById(R.id.tv_giagiohang);
        imggiohang=view.findViewById(R.id.img_giohang);
        btnminus=view.findViewById(R.id.btn_minus);
        btnplus=view.findViewById(R.id.btn_plus);
        btnvalues=view.findViewById(R.id.btn_values);
        tvtengiohang.setText(arryGioHang.get(i).getTensp());

        final DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        tvgiagiohang.setText(decimalFormat.format(arryGioHang.get(i).getGiasp())+" Đ");

        Log.d("test", String.valueOf(arryGioHang.size()));
        Log.d("test",arryGioHang.get(i).getHinhsp());

        Picasso.get().load(arryGioHang.get(i).getHinhsp()).centerCrop().resize(150,150).into(imggiohang);
        btnvalues.setText(arryGioHang.get(i).getSoluongsp()+"");

        final int sl= Integer.parseInt(btnvalues.getText().toString());
        final int slmoi=sl;

        if(sl<1){
            btnminus.setVisibility(View.INVISIBLE);
            btnplus.setVisibility(View.INVISIBLE);
        }
        else if(sl==1){
            btnminus.setVisibility(View.INVISIBLE);
            btnplus.setVisibility(View.VISIBLE);
        }
        else if(sl>=2&&sl<10){
            btnminus.setVisibility(View.VISIBLE);
            btnplus.setVisibility(View.VISIBLE);
        }
        else{
            btnminus.setVisibility(View.VISIBLE);
            btnplus.setVisibility(View.INVISIBLE);
        }

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.d("test11","chan doi");
                final AlertDialog.Builder buidlder=new AlertDialog.Builder(context);
                buidlder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không ?");
                buidlder.setIcon(android.R.drawable.ic_delete);
                buidlder.setTitle("Xác nhận xóa");
                buidlder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                buidlder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        MainActivity.mangGioHang.remove(i);
                        GioHangActivity.gioHangAdapter.notifyDataSetChanged();
                        if(MainActivity.mangGioHang.size()==0){
                            GioHangActivity.tvThongbao.setVisibility(View.VISIBLE);
                        }
                        long tong=0;
                        for(int i=0;i<MainActivity.mangGioHang.size();i++){
                            tong+=MainActivity.mangGioHang.get(i).getGiasp();
                        }
                        GioHangActivity.tvTongtien.setText(tong+"");
                    }
                });
                AlertDialog alertDialog=buidlder.create();
                alertDialog.show();
                return false;
            }
        });

        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl1=Integer.parseInt(btnvalues.getText().toString());
                int slmoi=Integer.parseInt(btnvalues.getText().toString());
                slmoi+=1;
                btnvalues.setText(slmoi+"");
                MainActivity.mangGioHang.get(i).setSoluongsp(slmoi);
                MainActivity.mangGioHang.get(i).setGiasp(MainActivity.mangGioHang.get(i).getGiasp()*slmoi/(sl1));
                tvgiagiohang.setText(MainActivity.mangGioHang.get(i).getGiasp()+"");
                long tongtien=0;
                for(int i=0;i<MainActivity.mangGioHang.size();i++){
                    tongtien+=MainActivity.mangGioHang.get(i).getGiasp();
                }
                DecimalFormat decimalFormat1=new DecimalFormat("###,###,###");
                GioHangActivity.tvTongtien.setText(decimalFormat1.format(tongtien)+" Đ");

                tvgiagiohang.setText(decimalFormat1.format(tongtien)+" Đ");
                if(slmoi>9){
                    btnplus.setVisibility(View.INVISIBLE);
                    btnminus.setVisibility(View.VISIBLE);
                    btnvalues.setText(slmoi+"");
                }
                else{
                    btnplus.setVisibility(View.VISIBLE);
                    btnminus.setVisibility(View.VISIBLE);
                    btnvalues.setText(slmoi+"");
                }
                notifyDataSetChanged ();
            }
        });

        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl1=Integer.parseInt(btnvalues.getText().toString());
                int slmoi=Integer.parseInt(btnvalues.getText().toString());
                slmoi-=1;
                btnvalues.setText(slmoi+"");
                MainActivity.mangGioHang.get(i).setSoluongsp(slmoi);
                MainActivity.mangGioHang.get(i).setGiasp(MainActivity.mangGioHang.get(i).getGiasp()*slmoi/(sl1));
                tvgiagiohang.setText(MainActivity.mangGioHang.get(i).getGiasp()+"");
                long tongtien=0;
                for(int i=0;i<MainActivity.mangGioHang.size();i++){
                    tongtien+=MainActivity.mangGioHang.get(i).getGiasp();
                }
                DecimalFormat decimalFormat1=new DecimalFormat("###,###,###");
                GioHangActivity.tvTongtien.setText(decimalFormat1.format(tongtien)+" Đ");
                tvgiagiohang.setText(decimalFormat1.format(tongtien)+" Đ");
                if(slmoi<2){
                    btnplus.setVisibility(View.VISIBLE);
                    btnminus.setVisibility(View.INVISIBLE);
                    btnvalues.setText(slmoi+"");
                }
                else{
                    btnplus.setVisibility(View.VISIBLE);
                    btnminus.setVisibility(View.VISIBLE);
                    btnvalues.setText(slmoi+"");
                }
                notifyDataSetChanged ();
            }
        });
        return view;
    }
//
//    @Override
//    public CharSequence[] getAutofillOptions() {
//        return new CharSequence[0];
//    }
}
