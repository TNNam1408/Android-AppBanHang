package com.appbanhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
import com.appbanhang.adapter.XeConTuDongAdapter;
import com.appbanhang.adapter.XeSoTuDongAdapter;
import com.appbanhang.model.SanPham;
import com.appbanhang.ultil.CheckConnection;
import com.appbanhang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XeConTuDongActivity extends AppCompatActivity {
    Toolbar toolbarxctd;
    ListView listViewxctd;
    XeConTuDongAdapter xeConTuDongAdapter;
    ArrayList<SanPham> mangxctd;
    int idxctd = 0;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_xecontudong);
        AnhXa();
        listViewxctd.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent ( XeConTuDongActivity.this, ChiTietActivity.class );
                intent.putExtra ( "chitietsanpham", mangxctd.get ( position ) );
                startActivity ( intent );
            }
        } );
        if(CheckConnection.haveNetworkConnection ( XeConTuDongActivity.this )){
            GetIDLoaiSanPham();
            ActionToolbar();
            //GetData(page);
            Getdata2();

        }else{
            CheckConnection.showToast_Short ( XeConTuDongActivity.this ,"Bạn hãy kiểm tra lại  kết nối" );
        }

    }

    private void Getdata2() {
        final RequestQueue request = Volley.newRequestQueue(XeConTuDongActivity.this);
        JsonArrayRequest json = new JsonArrayRequest( Server.duongdanxecontudong,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int id = 0, gia = 0;
                String tensanpham = "";
                String motasanpham = "", hinhanhsanpham = "";
                int idsanpham = 0;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        id = object.getInt("id");
                        tensanpham = object.getString("tensanpham");
                        gia = object.getInt("gia");
                        hinhanhsanpham = object.getString("hinhanhsanpham");
                        motasanpham = object.getString("motasanpham");
                        idsanpham = object.getInt("idsanpham");
                        mangxctd.add(new SanPham(id, tensanpham, gia, hinhanhsanpham, motasanpham, idsanpham));
                        xeConTuDongAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("kiemtra", error.toString());
                Toast.makeText(XeConTuDongActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.add(json);
    }


    private void ActionToolbar() {
        setSupportActionBar ( toolbarxctd );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbarxctd.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
    }

    private void GetIDLoaiSanPham() {
        idxctd = getIntent ().getIntExtra ( "idloaisanpham" ,-1);
        Log.d ("giatriloaisanpham", idxctd+"");
    }

    private void AnhXa() {
        toolbarxctd = (Toolbar) findViewById ( R.id.toolbarxecontudong );
        listViewxctd = (ListView) findViewById ( R.id.listviewxecontudong );
        mangxctd = new ArrayList<> ();
        xeConTuDongAdapter = new XeConTuDongAdapter ( getApplicationContext (),mangxctd );
        listViewxctd.setAdapter ( xeConTuDongAdapter );
    }
}
