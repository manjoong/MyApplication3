package com.example.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by 마루소프트 on 2018-02-07.
 */

public class DetailActivity extends AppCompatActivity {
    TextView txt_name;
    TextView txt_content;
    TextView tv_title;
    String name;
    String content;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initialize();
        initialize_toolbar();

    }

    public void initialize() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        TextView txt_name = (TextView) findViewById(R.id.txt_name);
        TextView txt_content = (TextView) findViewById(R.id.txt_content);
        txt_name.setFocusable(false);
        txt_name.setClickable(false);
        txt_content.setFocusable(false);
        txt_content.setClickable(false);



        name = getIntent().getStringExtra("name");
        content = getIntent().getStringExtra("content");

        txt_name.setText(name);
        txt_content.setText(content);



    }

    void initialize_toolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.icon_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        tv_title.setText(" ");
    }
    //백버튼 기능 활성화
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
