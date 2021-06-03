package com.appbanhang.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appbanhang.R;
import com.appbanhang.ultil.CheckConnection;
import com.appbanhang.ultil.Server;

import java.util.HashMap;
import java.util.Map;

public class QuanLySanPhamActivity extends AppCompatActivity {
    Toolbar toolbarqlsp;
    EditText tensanpham;
    EditText gia;
    EditText hinhanh;
    EditText mota;
    EditText idloaisanpham;
    Button capnhat;
    Button back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView( R.layout.activity_qlsanpham);

        Anhxa();
        back.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
        
        if(CheckConnection.haveNetworkConnection ( QuanLySanPhamActivity.this )){
            ActionToolbar();
            EventButton();

        }else{
            CheckConnection.showToast_Short ( QuanLySanPhamActivity.this ,"Bạn hãy kiểm tra lại  kết nối" );
        }
    }

    private void EventButton() {
        capnhat.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                final String tensp = tensanpham.getText ().toString ().trim ();
                final String giasp = gia.getText ().toString ().trim ();
                final String imagesp = hinhanh.getText ().toString ().trim ();
                final String motasp = mota.getText ().toString ().trim ();
                final String idlsp = idloaisanpham.getText ().toString ().trim ();
                if(tensp.length () > 0 && giasp.length ()>0 && imagesp.length ()>0 && motasp.length ()>0 && idlsp.length ()>0 ){
                    RequestQueue requestQueue = Volley.newRequestQueue ( QuanLySanPhamActivity.this );
                    StringRequest stringRequest = new StringRequest ( Request.Method.POST, Server.duongdancapnhatsanphammoi, new Response.Listener<String> () {
                        @Override
                        public void onResponse(String response) {
                            Log.d ( "masanpham",response );
                        }
                    }, new Response.ErrorListener () {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    } ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String, String> ();
                            hashMap.put ( "tensanpham",tensp );
                            hashMap.put ( "gia",giasp);
                            hashMap.put ( "hinhanhsanpham",imagesp);
                            hashMap.put ( "motasanpham",motasp);
                            hashMap.put ( "idsanpham",idlsp);
                            return hashMap;
                        }
                    };
                    requestQueue.add ( stringRequest );
                    Toast.makeText ( QuanLySanPhamActivity.this, "Thành công", Toast.LENGTH_SHORT ).show ();
                }else{
                    CheckConnection.showToast_Short ( QuanLySanPhamActivity.this, "Hãy kiểm tra lại dữ liệu" );
                }
            }
        } );
    }


    private void ActionToolbar() {
        setSupportActionBar ( toolbarqlsp );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbarqlsp.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
    }

    private void Anhxa() {
        toolbarqlsp = findViewById ( R.id.toolbarqlsanpham );
        tensanpham =findViewById ( R.id.edt_tensanpham );
        gia = findViewById ( R.id.edt_gia );
        hinhanh = findViewById ( R.id.edt_img );
        mota = findViewById ( R.id.edt_mota );
        idloaisanpham = findViewById ( R.id.edt_idloaisanpham );
        capnhat = findViewById ( R.id.btn_capnhat );
        back = findViewById ( R.id.btn_back );
    }
}

