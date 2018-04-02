package com.bjc.xcb.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bjc.xcb.demo.R;
import com.bjc.xcb.demo.refreshandloading.PullableListViewActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home_layout);
//        findViewById(R.id.btn_show_refresh).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, PullableListViewActivity.class));
//            }
//        });
//        findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, DownloadTengxunActivity.class));
//            }
//        });

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        Button btn1 = new Button(this);
        btn1.setText("RecyclerView");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, RecycleViewActivity.class));
            }
        });
        layout.addView(btn1);

        Button btn2 = new Button(this);
        btn2.setText("test");
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this, WebviewActivity.class));
            }
        });
        layout.addView(btn2);

        setContentView(layout);
    }
}
