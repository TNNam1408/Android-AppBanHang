package com.appbanhang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appbanhang.R;
import com.appbanhang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class KhachHangActivity extends AppCompatActivity {
    EditText edttenkhachhang,edtemail,edtsdt;
    Button btnXacNhan,btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        anhXa();
        eventButton();
    }

    private void eventButton() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ten=edttenkhachhang.getText().toString();
                final String sdt=edtsdt.getText().toString();
                final String email=edtemail.getText().toString();
                if(ten.length()>0&&sdt.length()>0&&email.length()>0){
                    final RequestQueue requestQueue= Volley.newRequestQueue(KhachHangActivity.this);
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang",madonhang);
                            if(Integer.parseInt(madonhang)>0){
                                RequestQueue requestQueue1=Volley.newRequestQueue(KhachHangActivity.this);
                                StringRequest stringRequest1=new StringRequest(Request.Method.POST, Server.duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1")){
                                            MainActivity.mangGioHang.clear();
                                            Toast.makeText(KhachHangActivity.this, "Bạn đã thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(KhachHangActivity.this,MainActivity.class));
                                            Toast.makeText(KhachHangActivity.this, "Mời bạn tiếp tục mua hàng", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(KhachHangActivity.this, "Lỗi không thêm giỏ hàng được", Toast.LENGTH_SHORT).show();
                                            Log.d("test",response);
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray=new JSONArray();
                                        for(int i=0;i<MainActivity.mangGioHang.size();i++){
                                            JSONObject jsonObject=new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("masanpham",MainActivity.mangGioHang.get(i).getIdsp());
                                                jsonObject.put("tensanpham",MainActivity.mangGioHang.get(i).getTensp());
                                                jsonObject.put("giasanpham",MainActivity.mangGioHang.get(i).getGiasp());
                                                jsonObject.put("soluongsanpham",MainActivity.mangGioHang.get(i).getSoluongsp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String>hashMap=new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                requestQueue1.add(stringRequest1);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String>hashMap=new HashMap<String, String>();
                            hashMap.put("tenkhachhang",ten);
                            hashMap.put("sodienthoai",sdt);
                            hashMap.put("email",email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
                else{
                    Toast.makeText(KhachHangActivity.this, "Hãy kiểm tra dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhXa() {
        edttenkhachhang= (EditText) findViewById(R.id.edt_tenkhachhang);
        edtemail= (EditText) findViewById(R.id.edt_email);
        edtsdt= (EditText) findViewById(R.id.edt_sdt);
        btnTroVe= (Button) findViewById(R.id.btn_trove);
        btnXacNhan= (Button) findViewById(R.id.btn_xacnhan);
    }
}
