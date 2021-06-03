package com.appbanhang.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.appbanhang.R;
import com.appbanhang.adapter.ChiTietDonHangAdapter;
import com.appbanhang.model.Chitietdonhang;
import com.appbanhang.ultil.CheckConnection;
import com.appbanhang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QLChiTietDonHangActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ChiTietDonHangAdapter adapter;
    ArrayList<Chitietdonhang> arrayList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_qlchitietdonhang );
        arrayList = new ArrayList<Chitietdonhang> ();

        if(CheckConnection.haveNetworkConnection ( QLChiTietDonHangActivity.this )){
            ClickAcToolbar();
            GetDataChitietsanpham();
            //Anhxa();

        }else{
            CheckConnection.showToast_Short ( QLChiTietDonHangActivity.this, "Không có kết nối Internet" );
            finish ();
        }

    }

    private void GetDataChitietsanpham() {
        final RequestQueue requestQueue = Volley.newRequestQueue ( QLChiTietDonHangActivity.this );
        JsonArrayRequest jsonx = new JsonArrayRequest ( Server.duongdanquanlyctdh, new Response.Listener<JSONArray> () {
            @Override
            public void onResponse(JSONArray response) {

                String id = "";
                String madh = "";
                String masp = "";
                String tensp = "";
                String gia = "";
                String soluong = "";
                for (int i = 0; i < response.length (); i++) {
                    try {
                        JSONObject object = response.getJSONObject ( i );
                        id = object.getString ( "id" );
                        madh = object.getString ( "madonhang" );
                        masp = object.getString ( "masanpham" );
                        tensp = object.getString ( "tensanpham" );
                        gia = object.getString ( "giasanpham" );
                        soluong = object.getString ( "soluongsanpham" );
                        arrayList.add ( new Chitietdonhang (id,madh,masp,tensp,gia,soluong ) );
                        //adapter.notifyDataSetChanged ();
                    } catch (JSONException e) {
                        e.printStackTrace ();
                    }
                }
                Anhxa();
            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d ( "Kiem tra", error.toString () );
                Toast.makeText ( QLChiTietDonHangActivity.this, error.toString (), Toast.LENGTH_SHORT ).show ();
            }
        } );
        requestQueue.add ( jsonx );
    }

    private void ClickAcToolbar() {
        toolbar = findViewById ( R.id.toolbarqlchitietdonhang );
        setSupportActionBar ( toolbar );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }
    private void Anhxa() {
        if(arrayList != null){

            recyclerView = findViewById ( R.id.recyclerviewqlchitietdonhang );
            adapter = new ChiTietDonHangAdapter ( this,arrayList );
            LinearLayoutManager layoutManager = new LinearLayoutManager (this);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager (QLChiTietDonHangActivity.this, 1));


        }
    }
}