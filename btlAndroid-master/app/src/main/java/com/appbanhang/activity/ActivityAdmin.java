package com.appbanhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.appbanhang.R;
import com.appbanhang.ultil.CheckConnection;

public class ActivityAdmin extends AppCompatActivity {
    Toolbar toolbaradmin;
    Button qlchitietsanpham;
    Button qlsanpham;
    Button qlkhach;
    Button qldonkhachhang;
    Button Logoutx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView( R.layout.activity_admin);
        anhxa();
        if(CheckConnection.haveNetworkConnection ( ActivityAdmin.this )){
            setButton ();
            setQlsanpham ();
            //setQlchitietsanpham();
            setspQlkhachhang();
            setspQLchitietdonhang();
        }else{
            CheckConnection.showToast_Short ( ActivityAdmin.this ,"Bạn hãy kiểm tra lại  kết nối" );
        }
    }

    private void anhxa() {
        qlsanpham = findViewById ( R.id.qlsanpham );
        qlchitietsanpham = findViewById ( R.id.qlsanphamadmin );
        qlkhach = findViewById ( R.id.qldonhang );
        qldonkhachhang = findViewById ( R.id.qlkhachhang );
        toolbaradmin =findViewById ( R.id.toolbaradmin );
        Logoutx = findViewById ( R.id.logoutadmin);
    }

    private void setQlsanpham(){
        qlsanpham.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityAdmin.this, QLSanPhamActivity.class);
                startActivity ( intent );
            }
        } );
    }
//    private void setQlchitietsanpham(){
//        qlchitietsanpham.setOnClickListener ( new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent (ActivityAdmin.this, QLSanPhamAdmin.class);
//                startActivity ( intent );
//            }
//        } );
//    }
    private void setspQlkhachhang(){
        qlkhach.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityAdmin.this, QLDonHangActivity.class);
                startActivity ( intent );
            }
        } );
    }

    private void setspQLchitietdonhang() {
        qldonkhachhang.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityAdmin.this, QLChiTietDonHangActivity.class);
                startActivity ( intent );
            }
        } );
    }

    private void setButton() {
        Logoutx.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityAdmin.this, ActivityLogin.class);
                startActivity ( intent );
                finish ();
                Toast.makeText ( ActivityAdmin.this, "Bạn đã Log Out thành công", Toast.LENGTH_SHORT ).show ();
            }
        } );
    }
}
