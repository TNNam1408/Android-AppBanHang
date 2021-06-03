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
import com.appbanhang.adapter.DonHangAdapter;
import com.appbanhang.model.DonHang;
import com.appbanhang.ultil.CheckConnection;
import com.appbanhang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QLDonHangActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    DonHangAdapter donHangAdapter;
    ArrayList<DonHang> mangDonHang;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_qldonhang );
        mangDonHang = new ArrayList<DonHang> ();
        Anhxaql();

        if(CheckConnection.haveNetworkConnection ( QLDonHangActivity.this )){
            ClickAcToolbar();
            getQLDonHang();
        }else{
            CheckConnection.showToast_Short ( QLDonHangActivity.this, "Không có kết nối Internet" );
            finish ();
        }
    }

    private void getQLDonHang() {
        final RequestQueue requestQueue = Volley.newRequestQueue ( QLDonHangActivity.this );
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest ( Server.duongdanquanlydonhang, new Response.Listener<JSONArray> () {
            @Override
            public void onResponse(JSONArray response) {
                String id = "";
                String tenkhach = "";
                String sdt = "";
                String email = "";
                for(int i =0;i<response.length ();i++){
                    try {
                        JSONObject object = response.getJSONObject ( i );
                        id = object.getString ( "id" );
                        tenkhach = object.getString ( "tenkhachhang" );
                        sdt = object.getString ( "sodienthoai" );
                        email = object.getString ( "email" );
                        mangDonHang.add ( new DonHang (id,tenkhach,sdt,email) );
                        donHangAdapter.notifyDataSetChanged ();
                        //Anhxaql();
                    } catch (JSONException e) {
                        e.printStackTrace ();
                    }

                }
            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d ("Kiem tra", error.toString ());
                Toast.makeText ( QLDonHangActivity.this, error.toString (), Toast.LENGTH_SHORT ).show ();
            }
        } );
        requestQueue.add ( jsonArrayRequest );
    }

    private void ClickAcToolbar() {
        setSupportActionBar ( toolbar );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    private void Anhxaql() {
       if(mangDonHang!=null){
           toolbar = findViewById ( R.id.toolbarqldonhang );
           recyclerView = findViewById ( R.id.recyclerviewqldonhang );

           donHangAdapter = new DonHangAdapter ( QLDonHangActivity.this,mangDonHang );
           LinearLayoutManager layoutManager = new LinearLayoutManager (this);
           layoutManager.setOrientation(RecyclerView.VERTICAL);
           recyclerView.setLayoutManager(layoutManager);
           //recyclerView.setHasFixedSize(true);
           recyclerView.setHasFixedSize(true);
           recyclerView.setLayoutManager(new GridLayoutManager (QLDonHangActivity.this, 1));
           recyclerView.setAdapter(donHangAdapter);
       }

    }
}
