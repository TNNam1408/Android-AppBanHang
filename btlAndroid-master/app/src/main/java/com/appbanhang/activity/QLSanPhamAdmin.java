package com.appbanhang.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.appbanhang.adapter.ChitietsanphamAdminAdapter;
import com.appbanhang.model.SanPham;
import com.appbanhang.ultil.CheckConnection;
import com.appbanhang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QLSanPhamAdmin extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ChitietsanphamAdminAdapter adapter;
    ArrayList<SanPham> arrayList;
    EditText Suatensp;
    EditText Suagiasp;
    EditText Suahinhanhsp;
    EditText Suamotasp;
    EditText Suaidlsp;
    Button Update, Back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_qlthongtinhang );

        arrayList = new ArrayList<> ();
        if(CheckConnection.haveNetworkConnection ( QLSanPhamAdmin.this )){
            ClickAcToolbar();
            getSanphamAdmin();

        }else{
            CheckConnection.showToast_Short ( QLSanPhamAdmin.this ,"Bạn hãy kiểm tra lại  kết nối" );
        }
    }

    public void xoaSanpham(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder ( QLSanPhamAdmin.this );
        builder.setMessage ( "Bạn có chắc chắn muốn xóa sản phẩm này không ?" );
        builder.setNegativeButton ( "Có", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String id = String.valueOf (arrayList.get (position).getId () );

                Toast.makeText ( QLSanPhamAdmin.this, "Xoa id ="+id, Toast.LENGTH_SHORT ).show ();

                if(id.length () >0){
                    RequestQueue queue = Volley.newRequestQueue ( QLSanPhamAdmin.this );
                    StringRequest request = new StringRequest ( Request.Method.POST, Server.duongdanxoasanpham, new Response.Listener<String> () {
                        @Override
                        public void onResponse(String response) {
                            Log.d ("xoa", response);
                            if ("1".equals ( response )){
                                Toast.makeText(QLSanPhamAdmin.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                            }
                            xoadanhsach();
                            getSanphamAdmin();
                        }
                    }, new Response.ErrorListener () {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    } ){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String> ();
                            hashMap.put ( "id", id);
                            return hashMap;
                        }
                    };
                    queue.add ( request );
                }else{
                    CheckConnection.showToast_Short ( QLSanPhamAdmin.this, "Hãy kiểm tra lại dữ liệu !" );
                }
            }
        } );
        builder.setPositiveButton ( "Không", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        } );
        builder.show ();
    }
    private void xoadanhsach() {
        for(int i=0 ;i<arrayList.size() ;i++){
            arrayList.remove(i);
            i--;
        }
        adapter.notifyDataSetChanged();
    }
    private void getSanphamAdmin() {
        final RequestQueue request = Volley.newRequestQueue(QLSanPhamAdmin.this);
        JsonArrayRequest json = new JsonArrayRequest(Server.duongdansanphammoinhat,new Response.Listener<JSONArray>() {
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
                        arrayList.add(new SanPham (id,tensanpham,gia,hinhanhsanpham,motasanpham,idsanpham));
//                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                AnhXa();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("kiemtra", error.toString());
                Toast.makeText(QLSanPhamAdmin.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.add(json);
    }

    public void suaSanpham(final int position){
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder ( this, R.style.Theme_AppCompat_DayNight_Dialog_Alert );
        }else{
            builder = new AlertDialog.Builder ( this );
        }
        LayoutInflater inflater = getLayoutInflater ();
        View view = inflater.inflate ( R.layout.activity_suasanpham, null );
        builder.setView ( view );
        final AlertDialog dialog = builder.create ();

        SanPham sanPham = arrayList.get ( position );

        Suatensp = view.findViewById ( R.id.edt_suatensanpham );
        Suagiasp = view.findViewById ( R.id.edt_suagia );
        Suahinhanhsp = view.findViewById ( R.id.edt_suaimg );
        Suamotasp = view.findViewById ( R.id.edt_suamota );
        Suaidlsp = view.findViewById ( R.id.edt_suaidloaisanpham );
        Update = view.findViewById ( R.id.btn_suacapnhat );
        Back = view.findViewById ( R.id.btn_suaback );

        Suatensp.setText ( sanPham.getTensanpham () );
        Suagiasp.setText ( String.valueOf ( sanPham.getGiasanpham () ) );
        Suahinhanhsp.setText ( sanPham.getHinhanhsanpham () );
        Suamotasp.setText ( sanPham.getMotasanpham () );
        Suaidlsp.setText ( String.valueOf ( sanPham.getIdsanpham () ) );

        Update.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(Suatensp.getText ().length ()!=0 &&
                   Suagiasp.getText ().length ()!=0 &&
                   Suahinhanhsp.getText ().length ()!=0 &&
                   Suamotasp.getText ().length ()!=0 &&
                   Suaidlsp.getText ().length ()!=0){

                        final String Id = String.valueOf ( arrayList.get ( position ).getId () );
                        final String Tensp = Suatensp.getText ().toString ().trim ();
                        final String Giasp = Suagiasp.getText ().toString ().trim ();
                        final String Hinhanhsp = Suahinhanhsp.getText ().toString ().trim ();
                        final String Motasp = Suamotasp.getText ().toString ().trim ();
                        final String Idloaisp = Suaidlsp.getText ().toString ().trim ();

                        if(Id.length () > 0 &&
                                Tensp.length () > 0 &&
                                Giasp.length () > 0 &&
                                Hinhanhsp.length () > 0 &&
                                Motasp.length () > 0 &&
                                Idloaisp.length () > 0){
                                    RequestQueue requestQueue = Volley.newRequestQueue ( QLSanPhamAdmin.this );
                                    StringRequest stringRequest = new StringRequest ( Request.Method.POST, Server.duongdansuasanpham, new Response.Listener<String> () {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d ( "sua", response );
                                            if("1".equals ( response )){
                                                Toast.makeText ( QLSanPhamAdmin.this, "Cập nhật sản phẩm thành công", Toast.LENGTH_SHORT ).show ();
                                            }
                                            xoadanhsach ();
                                            getSanphamAdmin ();
                                        }
                                    }, new Response.ErrorListener () {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    } ){
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            HashMap<String, String> hashMap = new HashMap<String,String> ();
                                            hashMap.put ( "id", Id );
                                            hashMap.put ( "tensanpham", Tensp );
                                            hashMap.put ( "gia", Giasp );
                                            hashMap.put ( "hinhanhsanpham", Hinhanhsp );
                                            hashMap.put ( "motasanpham", Motasp );
                                            hashMap.put ( "idsanpham", Idloaisp );
                                            return hashMap;
                                        }
                                    };
                                    requestQueue.add ( stringRequest );
                        }else{
                            CheckConnection.showToast_Short ( QLSanPhamAdmin.this, "Hãy kiểm tra lại dữ liệu !" );
                        }
                        dialog.cancel ();
                }else{
                    Toast.makeText ( QLSanPhamAdmin.this, "Vui lòng nhập đủ thông tin !", Toast.LENGTH_SHORT ).show ();
                }
            }
        } );
        Back.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                dialog.cancel ();
            }
        } );
        dialog.show ();
    }

    private void ClickAcToolbar() {
        toolbar = (Toolbar) findViewById ( R.id.toolbarqlthongtinhang );
        setSupportActionBar ( toolbar );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }
    private void AnhXa() {
        if(arrayList != null){
            recyclerView = findViewById ( R.id.recyclerviewqlthongtinhang );
           // arrayList = new ArrayList<SanPham>();
            adapter = new ChitietsanphamAdminAdapter (QLSanPhamAdmin.this, arrayList);
            LinearLayoutManager layoutManager = new LinearLayoutManager (this);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager (QLSanPhamAdmin.this, 1));
            recyclerView.setAdapter(adapter);
        }
    }
}
