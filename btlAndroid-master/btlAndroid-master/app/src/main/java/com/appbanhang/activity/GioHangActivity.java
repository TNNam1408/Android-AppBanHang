package com.appbanhang.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbanhang.R;
import com.appbanhang.adapter.GioHangAdapter;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    ListView lvGiohang;
    public static TextView tvThongbao,tvTongtien;
    Button btnThanhToan,btnTieptucmua;
    Toolbar toolbarGiohang;
    public static GioHangAdapter gioHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhXa();
        actionToolbar();
        checkData();
        eventUtil();
       // CatchonitemListView();
        eventButton();
    }

    private void eventButton() {
        btnTieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GioHangActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.mangGioHang.size()>0){
                    Intent intent=new Intent(GioHangActivity.this,KhachHangActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(GioHangActivity.this, "Bạn chưa có giỏ hàng nào cả", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CatchonitemListView() {

        lvGiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                Log.d("test11","chan doi");
                final AlertDialog.Builder buidlder=new AlertDialog.Builder(GioHangActivity.this);
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
                        gioHangAdapter.notifyDataSetChanged();
                        if(MainActivity.mangGioHang.size()==0){
                            tvThongbao.setVisibility(View.VISIBLE);
                        }
                        long tong=0;
                        for(int i=0;i<MainActivity.mangGioHang.size();i++){
                            tong+=MainActivity.mangGioHang.get(i).getGiasp();
                        }
                        tvTongtien.setText(tong+"");
                    }
                });
                AlertDialog alertDialog=buidlder.create();
                alertDialog.show();
                return false;
            }
        });
    }

    private void eventUtil() {
        long tongtien=0;
        for(int i=0;i<MainActivity.mangGioHang.size();i++){
            tongtien+=MainActivity.mangGioHang.get(i).getGiasp();
        }
        DecimalFormat decimalFomat=new DecimalFormat("###,###,###");
        tvTongtien.setText(decimalFomat.format(tongtien)+" Đ");
    }

    private void checkData() {
        if(MainActivity.mangGioHang.size()<=0){
            gioHangAdapter.notifyDataSetChanged();
            tvThongbao.setVisibility(View.VISIBLE);
            lvGiohang.setVisibility(View.INVISIBLE);
        }
        else{
            gioHangAdapter.notifyDataSetChanged();
            tvThongbao.setVisibility(View.INVISIBLE);
            lvGiohang.setVisibility(View.VISIBLE);
        }
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarGiohang);
        getSupportActionBar().setTitle("Giỏ Hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarGiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void anhXa() {
        lvGiohang= (ListView) findViewById(R.id.lv_giohang);
        tvThongbao= (TextView) findViewById(R.id.tv_thongbao);
        tvTongtien= (TextView) findViewById(R.id.tv_tongtien);
        btnThanhToan= (Button) findViewById(R.id.btn_thanhtoangiohang);
        btnTieptucmua= (Button) findViewById(R.id.btn_tieptucmuahang);
        toolbarGiohang= (Toolbar) findViewById(R.id.toolbar_giohang);
        gioHangAdapter=new GioHangAdapter(GioHangActivity.this,MainActivity.mangGioHang);
        lvGiohang.setAdapter(gioHangAdapter);
    }
}
