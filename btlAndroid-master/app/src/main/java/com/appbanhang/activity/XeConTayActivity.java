package com.appbanhang.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.appbanhang.R;
import com.appbanhang.adapter.XeConTayAdapter;
import com.appbanhang.model.SanPham;
import com.appbanhang.ultil.CheckConnection;
import com.appbanhang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class XeConTayActivity extends AppCompatActivity {
    Toolbar toolbarxct;
    ListView listViewxct;
    XeConTayAdapter xeConTayAdapter;
    ArrayList<SanPham> mangxct;
    int idxct = 0;
    //    int page = 1;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_xecontay);
        AnhXa();
        listViewxct.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText ( XeConTayActivity.this, ""+position, Toast.LENGTH_SHORT ).show ();
                Intent intent = new Intent (XeConTayActivity.this, ChiTietActivity.class);
                intent.putExtra ( "chitietsanpham", mangxct.get(position));
                startActivity (intent);
            }
        } );
        if(CheckConnection.haveNetworkConnection ( XeConTayActivity.this )){
            GetIDLoaiSanPham();
            ActionToolbar();
            //GetData(page);
            Getdata2();

        }else{
            CheckConnection.showToast_Short ( XeConTayActivity.this ,"Bạn hãy kiểm tra lại  kết nối" );
        }

    }

    private void Getdata2() {
        final RequestQueue request = Volley.newRequestQueue(XeConTayActivity.this);
        JsonArrayRequest json = new JsonArrayRequest( Server.duongdanxecontay,new Response.Listener<JSONArray>() {
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
                        mangxct.add(new SanPham(id, tensanpham, gia, hinhanhsanpham, motasanpham, idsanpham));
                        xeConTayAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("kiemtra", error.toString());
                Toast.makeText(XeConTayActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.add(json);
    }

    private void ActionToolbar() {
        setSupportActionBar ( toolbarxct );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbarxct.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
    }

    private void GetIDLoaiSanPham() {
        idxct = getIntent ().getIntExtra ( "idloaisanpham" ,-1);
        Log.d ("giatriloaisanpham", idxct+"");
    }

    private void AnhXa() {
        toolbarxct = (Toolbar) findViewById ( R.id.toolbarxecontay );
        listViewxct = (ListView) findViewById ( R.id.listviewxecontay );
        mangxct = new ArrayList<> ();
        xeConTayAdapter= new XeConTayAdapter( getApplicationContext (),mangxct );
        listViewxct.setAdapter ( xeConTayAdapter );
    }
}