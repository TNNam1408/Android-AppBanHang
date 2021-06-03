package com.appbanhang.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appbanhang.R;
import com.appbanhang.activity.QLSanPhamAdmin;
import com.appbanhang.model.SanPham;
import com.appbanhang.ultil.CheckConnection;
import com.appbanhang.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChitietsanphamAdminAdapter extends RecyclerView.Adapter<ChitietsanphamAdminAdapter.ItemHolder> {

    QLSanPhamAdmin context;
    ArrayList<SanPham> arrayList;

    public ChitietsanphamAdminAdapter(QLSanPhamAdmin context, ArrayList<SanPham> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.dong_quanlythongtinhang, null );
        ItemHolder itemHolder = new ItemHolder ( view );
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        if(holder != null){
            SanPham sanPham = arrayList.get ( position );
            holder.tensanphamadmin.setText ( sanPham.getTensanpham () );
            DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
            holder.giasanphamadmin.setText("Giá :"+decimalFormat.format(sanPham.getGiasanpham())+" VND");
            holder.motasanphamadmin.setText ( sanPham.getMotasanpham () );
            Picasso.get().load(sanPham.getHinhanhsanpham()).centerCrop().resize(150,150).into(holder.imgsanphamadmin);
        }
    }


    @Override
    public int getItemCount() {
        return arrayList.size ();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imgsanphamadmin;
        public TextView tensanphamadmin, giasanphamadmin, motasanphamadmin;
        public Button Menupopup;
        public ItemHolder(View itemView) {
            super ( itemView );
            imgsanphamadmin = itemView.findViewById ( R.id.img_chitietsanphamadmin );
            tensanphamadmin = itemView.findViewById ( R.id.tv_tenchitietsanphamadmin );
            giasanphamadmin = itemView.findViewById ( R.id.tv_giachitietsanphamadmin );
            motasanphamadmin = itemView.findViewById ( R.id.tv_motachitietsanphamadmin );
            Menupopup = itemView.findViewById ( R.id.menupopup );

            Menupopup.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(context, Menupopup);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @SuppressLint("NonConstantResourceId")
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.suasp:
                                    context.suaSanpham (getAdapterPosition ());
                                    Toast.makeText ( context,"Sua vitri: "+getAdapterPosition (),Toast.LENGTH_LONG ).show ();
                                    break;
                                case R.id.xoasp:
                                    context.xoaSanpham(getAdapterPosition ());
                                    Toast.makeText ( context,"Xoa vitri: "+getAdapterPosition (),Toast.LENGTH_LONG ).show ();
                                    break;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            } );
        }

        @Override
        public void onClick(View v) {

        }
    }
}
