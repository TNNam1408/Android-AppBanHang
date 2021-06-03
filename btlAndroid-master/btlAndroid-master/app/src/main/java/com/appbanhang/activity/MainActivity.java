package com.appbanhang.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.appbanhang.R;
import com.appbanhang.model.GioHang;
import com.appbanhang.model.SanPham;
import com.appbanhang.adapter.SanPhamAdapter;
import com.appbanhang.ultil.CheckConnection;
import com.appbanhang.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<SanPham> mangSanpham;
    SanPhamAdapter sanphamAdapter;
    DrawerLayout drawerLayout;
    Animation in, out;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    ListView lvManHinhChinh;
    int id = 0;
    public static ArrayList<GioHang> mangGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        if (CheckConnection.haveNetworkConnection(MainActivity.this)) {
            actionBar();
            addEEventViewFlipper();
            getdulieuSpmoinhat();
        } else {
            CheckConnection.showToast_Short(MainActivity.this, "khong co ket noi intenret");
            finish();
        }
    }

    private void getdulieuSpmoinhat() {
        final RequestQueue request = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest json = new JsonArrayRequest(Server.duongdansanphammoinhat,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int id = 0, gia = 0;
                String tensanham = "";
                String mota = "", hinhanh = "";
                int idsp = 0;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        id = object.getInt("id");
                        tensanham = object.getString("tensp");
                        gia = object.getInt("giasp");
                        hinhanh = object.getString("hinhanhsp");
                        mota = object.getString("motasp");
                        idsp = object.getInt("idsp");
                        mangSanpham.add(new SanPham(id, tensanham, gia, hinhanh, mota, idsp));
                        sanphamAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("kiemtra", error.toString());
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.add(json);
    }
    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Trang Chính");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
    }

    private void anhXa() {
        in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
        out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
        toolbar = (Toolbar) findViewById(R.id.toolbar_manhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerViewManHinhChinh = (RecyclerView) findViewById(R.id.recyclerview);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        lvManHinhChinh = (ListView) findViewById(R.id.lv_manhinhchinh);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mangSanpham = new ArrayList<SanPham>();
        sanphamAdapter = new SanPhamAdapter(MainActivity.this, mangSanpham);
        recyclerViewManHinhChinh.setHasFixedSize(true);
        recyclerViewManHinhChinh.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        recyclerViewManHinhChinh.setAdapter(sanphamAdapter);
        if (mangGioHang != null) {

        } else {
            mangGioHang = new ArrayList<>();
        }
    }

    private void addEEventViewFlipper() {
        ArrayList<String> arrQuangCao = new ArrayList<>();
        arrQuangCao.add("http://doanhnhanvietuc.com/wp-content/uploads/2017/04/steve-jobs-apple-image-10-1491902249560-crop-1491902262455.jpg");
        arrQuangCao.add("http://vnreview.vn/image/15/60/54/1560540.jpg?t=1471693892875");
        arrQuangCao.add("https://kenh14cdn.com/2016/mac-1477590136039.jpg");
        arrQuangCao.add("https://www.thongtincongnghe.com/sites/default/files/images/2012/1/13/img-1326420488-2.jpg");
        for (int i = 0; i < arrQuangCao.size(); i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            Picasso.get().load(arrQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setAutoStart(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_giohang) {
            Intent intent = new Intent(MainActivity.this, GioHangActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public class readJson extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder=new StringBuilder();
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            try {
                JSONArray jsonArray=new JSONArray(s);
                int id = 0, gia = 0;
                String tensanham = "";
                String mota = "", hinhanh = "";
                int idsp = 0;
                Log.d("kiemtra",jsonArray.toString()+"chn doi");
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject object = jsonArray.getJSONObject(i);
                        id = object.getInt("id");
                        tensanham = object.getString("tensp");
                        gia = object.getInt("giasp");
                        hinhanh = object.getString("hinhanhsp");
                        mota = object.getString("motasp");
                        idsp = object.getInt("idsp");
                        mangSanpham.add(new SanPham(id, tensanham, gia, hinhanh, mota, idsp));
                        sanphamAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
