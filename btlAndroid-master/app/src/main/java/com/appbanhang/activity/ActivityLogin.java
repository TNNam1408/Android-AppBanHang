package com.appbanhang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
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

public class ActivityLogin extends AppCompatActivity {
    Button buttonlogin;
    Button buttonsignup;
    EditText editTextEmail;
    EditText editTextPass;
    String ten, mk;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView( R.layout.activity_login);
        buttonlogin = findViewById ( R.id.login );
        buttonsignup = findViewById ( R.id.signup );
        editTextEmail = findViewById ( R.id.email );
        editTextPass = findViewById ( R.id.pass );

        buttonsignup.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog ( ActivityLogin.this );
                dialog.setTitle ( "Hộp thoại sử lý" );
                dialog.setCancelable ( false );
                dialog.setContentView ( R.layout.cuttondialog );

                final EditText editUser = dialog.findViewById ( R.id.usersignup );
                final EditText editPass = dialog.findViewById ( R.id.passwordsignup );

                Button buttonhuy = dialog.findViewById ( R.id.huy );
                Button buttondongy = dialog.findViewById ( R.id.dongy );

                buttondongy.setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        ten = editUser.getText ().toString ().trim ();
                        mk = editPass.getText ().toString ().trim ();
//
                        editTextEmail.setText ( ten );
                        editTextPass.setText ( mk );

                        if(editUser.length () >0 && editPass.length () >0){
                            RequestQueue requestQueue = Volley.newRequestQueue ( ActivityLogin.this );
                            StringRequest stringRequest = new StringRequest ( Request.Method.POST, Server.duongdansignup, new Response.Listener<String> () {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText ( ActivityLogin.this,response, Toast.LENGTH_SHORT ).show ();
                                }
                            }, new Response.ErrorListener () {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText ( ActivityLogin.this,error +"", Toast.LENGTH_SHORT ).show ();
                                }
                            } ){
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String, String> hashMap = new HashMap<String, String> ();
                                    hashMap.put ( "user", ten );
                                    hashMap.put ( "password", mk );
                                    return hashMap;

                                }
                            };
                            requestQueue.add ( stringRequest );
//                            Toast.makeText ( ActivityLogin.this, "Đăng kí thành công", Toast.LENGTH_LONG ).show ();
                        }else {
                            CheckConnection.showToast_Short ( ActivityLogin.this, "Hãy kiểm tra lại dữ liệu" );
                        }
                        dialog.cancel ();
                    }
                } );
                buttonhuy.setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel ();
                    }
                } );
                dialog.show ();
            }
        } );

//        editTextEmail.setText ( "123" );
//        editTextPass.setText ( "123" );

//        buttonlogin.setOnClickListener ( new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//
//                if(editTextEmail.getText ().length ()!=0 &&editTextPass.getText ().length ()!=0){
//
//                    if(editTextEmail.getText ().toString ().equals ( ten ) && editTextPass.getText ().toString ().equals ( mk )){
//
//                        Toast.makeText ( ActivityLogin.this, "Bạn đã đăng nhập thành công", Toast.LENGTH_SHORT ).show ();
//                        Intent intent = new Intent (ActivityLogin.this,MainActivity.class);
//                        startActivity ( intent );
//
//                    }else if(editTextEmail.getText ().toString ().equals ( "123" ) && editTextPass.getText ().toString ().equals ( "123" )){
//
//                        Toast.makeText ( ActivityLogin.this, "Bạn đã đăng nhập thành công", Toast.LENGTH_SHORT ).show ();
//                        Intent intent = new Intent (ActivityLogin.this,MainActivity.class);
//                        startActivity ( intent );
//
//                    }else if(editTextEmail.getText ().toString ().equals ( "admin" ) && editTextPass.getText ().toString ().equals ( "admin" )){
//
//                        Toast.makeText ( ActivityLogin.this, "Bạn đã đăng nhập thành công", Toast.LENGTH_SHORT ).show ();
//                        Intent intent = new Intent (ActivityLogin.this,ActivityAdmin.class);
//                        startActivity ( intent );
//
//                    }
//                    else {
//                        Toast.makeText ( ActivityLogin.this, "Bạn đã đăng nhập thất bại", Toast.LENGTH_SHORT ).show ();
//                    }
//                }else{
//                    Toast.makeText ( ActivityLogin.this, "Mời bạn nhập đủ thông tin", Toast.LENGTH_SHORT ).show ();
//                }
//            }
//        });

        buttonlogin.setOnClickListener ( new View.OnClickListener () {

            @Override
            public void onClick(View v) {
                if(editTextEmail.getText ().length ()!=0 &&editTextPass.getText ().length ()!=0) {
                    RequestQueue requestQueue2 = Volley.newRequestQueue ( ActivityLogin.this );
                    StringRequest stringRequest2 = new StringRequest ( Request.Method.POST,Server.duongdanloginaccount, new Response.Listener<String> () {
                        @Override
                        public void onResponse(String response) {
                            String s = response.trim ();

                            if (s.equalsIgnoreCase ( "Dang nhap thanh cong" )) {
                                Intent intent = new Intent ( ActivityLogin.this, MainActivity.class );
                                startActivity ( intent );

                            } else {
                                Toast.makeText ( ActivityLogin.this, response, Toast.LENGTH_SHORT ).show ();
                            }
                            if (editTextEmail.getText ().toString ().equals ( "admin" ) && editTextPass.getText ().toString ().equals ( "admin" )) {

                                Toast.makeText ( ActivityLogin.this, "Bạn đã đăng nhập thành công", Toast.LENGTH_SHORT ).show ();
                                Intent intent = new Intent ( ActivityLogin.this, ActivityAdmin.class );
                                startActivity ( intent );

                            }
//                            else {
//                                //Toast.makeText ( ActivityLogin.this, "Bạn đã đăng nhập thất bại", Toast.LENGTH_SHORT ).show ();
//                            }
                        }
                    }, new Response.ErrorListener () {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText ( ActivityLogin.this, error+"", Toast.LENGTH_SHORT ).show ();
                        }
                    } ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String> ();
                            ten = editTextEmail.getText ().toString ().trim ();
                            mk = editTextPass.getText ().toString ().trim ();
                            hashMap.put ( "user", ten );
                            hashMap.put ( "password", mk );
                            return hashMap;
                        }
                    };
                    requestQueue2.add ( stringRequest2 );
                }
            }
        } );
    }
}
