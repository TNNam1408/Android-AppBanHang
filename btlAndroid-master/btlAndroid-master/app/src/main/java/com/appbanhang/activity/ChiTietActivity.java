package com.appbanhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.appbanhang.R;
import com.appbanhang.adapter.GioHangAdapter;
import com.appbanhang.model.GioHang;
import com.appbanhang.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietActivity extends AppCompatActivity{
    GioHangAdapter gioHangAdapter;
    Toolbar toolbarChiTiet;
    ImageView imgChiTiet;
    TextView tvTen,tvGia,tvMoTa;
    Spinner spinner;
    Button btnDatMua;
    int id=0;
    String tenchitiet="";
    int giachitiet=0;
    String motachitiet="",hinhanhchitiet="";
    int idsanpham=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        anhXa();
        eventactionBar();
        getInformation();
        eventSpinner();
        addEvent();
    }

    private void addEvent() {
        btnDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.mangGioHang.size()>0){
                    int sl= Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exsist=false;
                    for(int i=0;i<MainActivity.mangGioHang.size();i++){
                        if(MainActivity.mangGioHang.get(i).getIdsp()==id){
                            MainActivity.mangGioHang.get(i).setSoluongsp(MainActivity.mangGioHang.get(i).getSoluongsp()+sl);
                            if(MainActivity.mangGioHang.get(i).getSoluongsp()>=10){
                                MainActivity.mangGioHang.get(i).setSoluongsp(10);
                            }
                            MainActivity.mangGioHang.get(i).setGiasp(giachitiet*sl+MainActivity.mangGioHang.get(i).getGiasp());
                            exsist=true;
                        }
                    }
                    if(exsist==false){
                        int soluong= Integer.parseInt(spinner.getSelectedItem().toString());
                        long giamoi=soluong*giachitiet;
                        MainActivity.mangGioHang.add(new GioHang(id,tenchitiet,giamoi,hinhanhchitiet,soluong));
                        gioHangAdapter.notifyDataSetChanged();
                    }
                }else{
                    int soluong= Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi=soluong*giachitiet;
                    MainActivity.mangGioHang.add(new GioHang(id,tenchitiet,giamoi,hinhanhchitiet,soluong));
                    gioHangAdapter.notifyDataSetChanged();
                }
                Intent intent=new Intent(ChiTietActivity.this,GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void eventSpinner() {
        ArrayList<Integer>arr=new ArrayList<Integer>();
        for(int i=1;i<=10;i++){
            arr.add(i);
        }
        ArrayAdapter adapter=new ArrayAdapter(ChiTietActivity.this,android.R.layout.simple_spinner_item,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void getInformation() {
        SanPham sanPham= (SanPham) getIntent().getSerializableExtra("chitietsanpham");
        id=sanPham.getId();
        tenchitiet=sanPham.getTensanpham();
        giachitiet=sanPham.getGiasanpham();
        motachitiet=sanPham.getMotasanpham();
        hinhanhchitiet=sanPham.getHinhanhsanpham();
        idsanpham=sanPham.getIdsanpham();
        tvTen.setText(tenchitiet);
        tvMoTa.setText(motachitiet);
        DecimalFormat deci=new DecimalFormat("###,###,###");
        tvGia.setText("Giá "+deci.format(giachitiet)+" Đ");
        idsanpham=sanPham.getIdsanpham();
        Picasso.get().load(sanPham.getHinhanhsanpham()).error(R.drawable.error).into(imgChiTiet);
    }

    private void eventactionBar() {
        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setTitle("Chi Tiết Sản Phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhXa() {
        toolbarChiTiet= (Toolbar) findViewById(R.id.toolbar_chitietsanpham);
        imgChiTiet= (ImageView) findViewById(R.id.img_chitietsanpham);
        tvTen= (TextView) findViewById(R.id.tv_tenchitietsanpham);
        tvGia= (TextView) findViewById(R.id.tv_giachitietsanpham);
        tvMoTa= (TextView) findViewById(R.id.tv_motachitietsanpham);
        spinner= (Spinner) findViewById(R.id.spinner);
        btnDatMua= (Button) findViewById(R.id.btn_datmua);
        gioHangAdapter=new GioHangAdapter(ChiTietActivity.this,MainActivity.mangGioHang);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_giohang){
            Intent intent=new Intent(ChiTietActivity.this,GioHangActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
