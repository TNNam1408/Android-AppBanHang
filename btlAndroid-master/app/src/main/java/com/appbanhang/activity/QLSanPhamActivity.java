package com.appbanhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.appbanhang.R;
import com.appbanhang.ultil.CheckConnection;

public class QLSanPhamActivity extends AppCompatActivity {
    TextView themsp, spadmin;
    Toolbar toolbar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_quanlysanpham );

        anhxa();
        if(CheckConnection.haveNetworkConnection ( QLSanPhamActivity.this )){
            setThemsanpham ();
            setQlchitietsanpham();
            ActionToolbar();
        }else{
            CheckConnection.showToast_Short ( QLSanPhamActivity.this ,"Bạn hãy kiểm tra lại  kết nối" );
        }
    }

    private void setQlchitietsanpham(){
        spadmin.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (QLSanPhamActivity.this, QLSanPhamAdmin.class);
                startActivity ( intent );
            }
        } );
    }

    private void setThemsanpham() {

        themsp.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (QLSanPhamActivity.this, QuanLySanPhamActivity.class);
                startActivity ( intent );
            }
        } );
    }

    private void ActionToolbar() {
        setSupportActionBar ( toolbar);
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
    }
    private void anhxa() {
        toolbar = findViewById ( R.id.toolbarqlsp );
        themsp = findViewById ( R.id.themsp );
        spadmin = findViewById ( R.id.qlsanphamadmin );
    }
}
