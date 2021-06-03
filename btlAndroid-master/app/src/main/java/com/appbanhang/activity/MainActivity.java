package com.appbanhang.activity;

import android.app.SearchManager;
import android.content.Context;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.appbanhang.adapter.LoaiSanPhamAdapter;
import com.appbanhang.model.GioHang;
import com.appbanhang.model.LoaiSanPham;
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
    ArrayList<LoaiSanPham> mangLoaisanpham;
    LoaiSanPhamAdapter loaiSanPhamAdapter;

    ArrayList<SanPham> mangSanpham;
    ArrayList<SanPham> mangSanpham2;
    SanPhamAdapter sanphamAdapter;
    DrawerLayout drawerLayout;
    Animation in, out;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    ListView lvManHinhChinh;
    Button Logout;
    SearchView searchView;
    int id = 0;
    public static ArrayList<GioHang> mangGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();

        searchView.setOnQueryTextListener ( new SearchView.OnQueryTextListener () {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sanphamAdapter.getFilter ().filter ( newText );

                return false;
            }
        } );

        Logout.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intentL = new Intent (MainActivity.this, ActivityLogin.class);
                startActivity ( intentL );
                finish ();
                Toast.makeText ( MainActivity.this, "Bạn đã Log Out thành công", Toast.LENGTH_SHORT ).show ();
            }
        } );

        if (CheckConnection.haveNetworkConnection(MainActivity.this)) {
            //actionBar();
            actiontoolbar ();
            addEEventViewFlipper();
            getdulieuSpmoinhat();
            getdulieuloaisanpham();
            CatchOnItemListView();
        } else {
            CheckConnection.showToast_Short(MainActivity.this, "khong co ket noi intenret");
            finish();
        }
    }


    private void CatchOnItemListView() {
        lvManHinhChinh.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //trang chính
                    case 0:
                        if (CheckConnection.haveNetworkConnection ( getApplicationContext () )) {
                            Intent intent = new Intent ( MainActivity.this, MainActivity.class );
                            startActivity ( intent );
                        } else {
                            CheckConnection.showToast_Short ( getApplicationContext (), "Bạn hãy kiểm tra lại kết nối //" );
                        }
                        drawerLayout.closeDrawer ( GravityCompat.START );
                        break;
                    //Xe số tự động
                    case 1:
                        if(CheckConnection.haveNetworkConnection ( getApplicationContext () )){
                            Intent intent = new Intent (MainActivity.this, XeSoTuDongActivity.class);
                            startActivity ( intent );
                            intent.putExtra ( "idloaisanpham", mangLoaisanpham.get ( position ).getId () );
                        }else{
                            CheckConnection.showToast_Short ( getApplicationContext (),"Bạn hãy kiểm tra lại kết nối //" );
                        }
                        drawerLayout.closeDrawer ( GravityCompat.START );
                        break;
                    //xe côn tay
                    case 2:
                        if(CheckConnection.haveNetworkConnection ( getApplicationContext () )){
                            Intent intent = new Intent (MainActivity.this, XeConTayActivity.class);
                            startActivity ( intent );
                            intent.putExtra ( "idloaisanpham", mangLoaisanpham.get ( position ).getId () );
                        }else{
                            CheckConnection.showToast_Short ( getApplicationContext (),"Bạn hãy kiểm tra lại kết nối //" );
                        }
                        drawerLayout.closeDrawer ( GravityCompat.START );
                        break;
                    //xe côn tự động
                    case 3:
                        if(CheckConnection.haveNetworkConnection ( getApplicationContext () )){
                            Intent intent = new Intent (MainActivity.this, XeConTuDongActivity.class);
                            startActivity ( intent );
                            intent.putExtra ( "idloaisanpham", mangLoaisanpham.get ( position ).getId () );
                        }else{
                            CheckConnection.showToast_Short ( getApplicationContext (),"Bạn hãy kiểm tra lại kết nối //" );
                        }
                        drawerLayout.closeDrawer ( GravityCompat.START );
                        break;
//                    //Liên hệ
                    case 4:
                        if(CheckConnection.haveNetworkConnection ( getApplicationContext () )){
                            Intent intent = new Intent (MainActivity.this, LienHeActivity.class);

                            startActivity ( intent );
                        }else{
                            CheckConnection.showToast_Short ( getApplicationContext (),"Bạn hãy kiểm tra lại kết nối //" );
                        }
                        drawerLayout.closeDrawer ( GravityCompat.START );
                        break;
//                    //Thông Tin
//                    case 5:
//                        if(CheckConnection.haveNetworkConnection ( getApplicationContext () )){
//                            Intent intent = new Intent (MainActivity.this,thongtinactivity.class);
//                            startActivity ( intent );
//                        }else{
//                            CheckConnection.showToast_Short ( getApplicationContext (),"Bạn hãy kiểm tra lại kết nối //" );
//                        }
//                        drawerLayout.closeDrawer ( GravityCompat.START );
//                        break;
                }
            }
        } );
    }

    //get loai san pham
    private void getdulieuloaisanpham() {
        final RequestQueue request = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest json = new JsonArrayRequest(Server.duongdanloaisanpham,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int id = 0;
                String tenloaisanpham = "";
                String hinhanhloaisanpham = "";
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        id = object.getInt("id");
                        tenloaisanpham = object.getString("tenloaisanpham");
                        hinhanhloaisanpham = object.getString("hinhanhloaisanpham");
                        mangLoaisanpham.add(new LoaiSanPham (id,tenloaisanpham,hinhanhloaisanpham));
                        loaiSanPhamAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mangLoaisanpham.add ( new LoaiSanPham ( 4,"Liên Hệ", "https://voip24h.vn/wp-content/uploads/2019/03/phone-png-mb-phone-icon-256.png" ) );
                mangLoaisanpham.add ( new LoaiSanPham ( 5,"Thông Tin", "https://upload.wikimedia.org/wikipedia/commons/0/01/Windows_Terminal_Logo_256x256.png" ) );

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
    //get du lieu cho san pham moi nhat
    private void getdulieuSpmoinhat() {
        final RequestQueue request = Volley.newRequestQueue(MainActivity.this);
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
                        mangSanpham.add(new SanPham(id, tensanpham, gia, hinhanhsanpham, motasanpham, idsanpham));
                        mangSanpham2.add (new SanPham(id, tensanpham, gia, hinhanhsanpham, motasanpham, idsanpham));
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
    private void actiontoolbar() {
        setSupportActionBar ( toolbar );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer ( GravityCompat.START );
            }
        } );
    }

    private void anhXa() {
        in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
        out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
        toolbar = (Toolbar) findViewById(R.id.toolbar_manhinhchinh);
        Logout = findViewById ( R.id.logout );
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerViewManHinhChinh = (RecyclerView) findViewById(R.id.recyclerview);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        lvManHinhChinh = (ListView) findViewById(R.id.lv_manhinhchinh);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        //Loai San Pham
        mangLoaisanpham = new ArrayList<LoaiSanPham> ();

        loaiSanPhamAdapter = new LoaiSanPhamAdapter ( mangLoaisanpham, MainActivity.this );
        lvManHinhChinh.setAdapter ( loaiSanPhamAdapter );
        mangLoaisanpham.add (0, new LoaiSanPham (0, "Trang Chính", "https://vietwebgroup.vn/admin/uploads/trang-chu-la-gi-tim-hieu-ve-trang-chu-la-gi.png"));

        //Search view
        searchView = findViewById ( R.id.searchview );

        //San pham
        mangSanpham = new ArrayList<SanPham>();
        mangSanpham2 = new ArrayList<> ();
        sanphamAdapter = new SanPhamAdapter(MainActivity.this, mangSanpham,mangSanpham2);
        recyclerViewManHinhChinh.setHasFixedSize(true);
        recyclerViewManHinhChinh.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        recyclerViewManHinhChinh.setAdapter(sanphamAdapter);
        //gio hang
        if (mangGioHang != null) {

        } else {
            mangGioHang = new ArrayList<>();
        }
    }

    private void addEEventViewFlipper() {
        ArrayList<String> arrQuangCao = new ArrayList<>();
        arrQuangCao.add("https://scontent.fhan5-6.fna.fbcdn.net/v/t1.0-9/118286154_1634000436778257_5019162894411424407_o.jpg?_nc_cat=107&ccb=3&_nc_sid=730e14&_nc_ohc=hiJsnN6EvfoAX9mP8my&_nc_ht=scontent.fhan5-6.fna&oh=345aeaa108ae806d41cb73adde4253c6&oe=604F4FDA");
        arrQuangCao.add("https://scontent.fhan5-6.fna.fbcdn.net/v/t1.0-9/143171175_1774493876062245_272925990996184457_o.jpg?_nc_cat=107&ccb=3&_nc_sid=730e14&_nc_ohc=rc5qEq8vdx0AX-_q-j-&_nc_ht=scontent.fhan5-6.fna&oh=063fc7337686c757cbba8f62811df8c9&oe=6050CA87");
        arrQuangCao.add("https://vcdn-vnexpress.vnecdn.net/2020/02/09/2020-ducati-panigale-v4-superl-9612-8785-1581214632.jpg");
        arrQuangCao.add("https://motosaigon.vn/wp-content/uploads/2020/02/04_Ducati-Superleggera-V4_UC145952_Mid.jpg");
        arrQuangCao.add("https://www.cycleworld.com/resizer/-kb7y5B_KqK-tNbisyXfHf_NnHo=/760x570/arc-anglerfish-arc2-prod-bonnier.s3.amazonaws.com/public/A4QIXZO3OGIDHYUIHQ7UOP4UKE.jpg");
        arrQuangCao.add("https://giaxe.2banh.vn/dataupload/products/images/1574670399-2a1f55c948a112d4f9730779e7a5ec61.jpg");
        arrQuangCao.add("https://image.xedoisong.vn/Uploaded/duchieu/2019_03_29/HondaCivic_AKdecal/xedoisong_AK_Decal_honda_civic_tokyo_Style_wrap_khoa_sen_h1_EHVY.jpg");
        arrQuangCao.add("https://cms-i.autodaily.vn/du-lieu/2020/07/21/lamborghini-aventador-svj-xago-edition-1.jpg");
        arrQuangCao.add ( "https://scontent.fhan5-2.fna.fbcdn.net/v/t1.0-9/131551568_837875173450733_4665033967774470560_o.jpg?_nc_cat=102&ccb=3&_nc_sid=e3f864&_nc_ohc=zmpOwPj-lCIAX9MDbXc&_nc_ht=scontent.fhan5-2.fna&oh=c9a552345dd118a757a15a0480b2f156&oe=60504E7F" );
        for (int i = 0; i < arrQuangCao.size(); i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            Picasso.get().load(arrQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(2000);
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
