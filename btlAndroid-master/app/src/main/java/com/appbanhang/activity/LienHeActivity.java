package com.appbanhang.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.appbanhang.R;

public class LienHeActivity extends AppCompatActivity {

    Toolbar toolbar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_lienhe );
        toolbar = findViewById ( R.id.toolbarlienhe );
        ActionToolbar ();
    }

    private void ActionToolbar() {
        setSupportActionBar ( toolbar );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
    }
}
