package com.appbanhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
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

public class XeSoTuDongActivity extends AppCompatActivity {

    Toolbar toolbarxstd;
    ListView listViewxstd;
    XeSoTuDongAdapter xeSoTuDongAdapter;
    ArrayList<SanPham> mangxstd;
    int idxstd = 0;
//    int page = 1;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_xesotudong );

        AnhXa();
        listViewxstd.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent ( XeSoTuDongActivity.this, ChiTietActivity.class );
                intent.putExtra ( "chitietsanpham", mangxstd.get ( position ) );
                startActivity ( intent );
            }
        } );
        if(CheckConnection.haveNetworkConnection ( XeSoTuDongActivity.this )){
            GetIDLoaiSanPham();
            ActionToolbar();
            //GetData(page);
            Getdata2();

        }else{
            CheckConnection.showToast_Short ( XeSoTuDongActivity.this ,"Bạn hãy kiểm tra lại  kết nối" );
        }

    }

    private void Getdata2() {
            final RequestQueue request = Volley.newRequestQueue(XeSoTuDongActivity.this);
            JsonArrayRequest json = new JsonArrayRequest(Server.duongdanxesotudong,new Response.Listener<JSONArray>() {
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
                            mangxstd.add(new SanPham(id, tensanpham, gia, hinhanhsanpham, motasanpham, idsanpham));
                            xeSoTuDongAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("kiemtra", error.toString());
                    Toast.makeText(XeSoTuDongActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            request.add(json);
        }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue ( XeSoTuDongActivity.this  );
        String duongdan = Server.duongdanxesotudong + String.valueOf ( Page );
        StringRequest stringRequest = new StringRequest ( Request.Method.POST, duongdan, new Response.Listener<String> () {
            @Override
            public void onResponse(String response) {
                int Id = 0;
                String tenxstd = "";
                int gia = 0;
                String hinhanhxstd = "";
                String motaxstd = "";
                int Idxstd = 0;
                if(response != null){
                    try {
                        JSONArray jsonArray = new JSONArray (response);
                        for (int i = 0 ;i < jsonArray.length ();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject ( i );
                            Id = jsonObject.getInt ( "id" );
                            tenxstd = jsonObject.getString ( "tensanpham" );
                            gia = jsonObject.getInt ( "gia" );
                            hinhanhxstd = jsonObject.getString ( "hinhanhsanpham" );
                            motaxstd = jsonObject.getString ( "motasanpham" );
                            Idxstd = jsonObject.getInt ( "idsanpham" );
                            mangxstd.add ( new SanPham (Id,tenxstd,gia,hinhanhxstd,motaxstd,Idxstd));
                            xeSoTuDongAdapter.notifyDataSetChanged ();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace ();
                    }
                }
            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String> ();
                param.put ( "idsanpham", String.valueOf ( idxstd ));
                return param;
            }
        };
        requestQueue.add ( stringRequest );
    }


    private void ActionToolbar() {
        setSupportActionBar ( toolbarxstd );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbarxstd.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
    }

    private void GetIDLoaiSanPham() {
        idxstd = getIntent ().getIntExtra ( "idloaisanpham" ,-1);
        Log.d ("giatriloaisanpham", idxstd+"");
    }

    private void AnhXa() {
        toolbarxstd = (Toolbar) findViewById ( R.id.toolbarxesotudong );
        listViewxstd = (ListView) findViewById ( R.id.listviewxesotudong );
        mangxstd = new ArrayList<> ();
        xeSoTuDongAdapter = new XeSoTuDongAdapter ( getApplicationContext (),mangxstd );
        listViewxstd.setAdapter ( xeSoTuDongAdapter );
    }
}
