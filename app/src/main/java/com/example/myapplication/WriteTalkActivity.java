package com.example.myapplication;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.myapplication.APIservice.API_URL;

/**
 * Created by 마루소프트 on 2018-01-29.
 */

public class WriteTalkActivity extends AppCompatActivity {
    private EditText input_id;
    private EditText input_pwd;
    private EditText input_title;
    private EditText input_content;
    TextView tv_title;
    ImageButton btn_write;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_talk);
        input_id = findViewById(R.id.input_id);
        input_pwd = findViewById(R.id.input_pwd);
        input_title = findViewById(R.id.input_title);
        input_content = findViewById(R.id.input_content);
        tv_title = (TextView) findViewById(R.id.tv_title);
        btn_write = (ImageButton) findViewById(R.id.btn_write);


        //공유버튼 클릭시! alert창
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(WriteTalkActivity.this);
                alert_confirm.setMessage("글을 게시하겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                post();
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();

            }
        });

        initialize_toolbar();
        index();
    }

    public void index() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIservice retrofitService = retrofit.create(APIservice.class);
        Call<Talk_CallBackItem> call = retrofitService.getTalk("0");
        call.enqueue(new Callback<Talk_CallBackItem>() {
            @Override
            public void onResponse(Call<Talk_CallBackItem> call, Response<Talk_CallBackItem> response) {
                Log.d("김만중", response.body().getData().toString());
//                textViewIndex.setText(response.body().getData().get(0).getPassword().toString());
            }

            @Override
            public void onFailure(Call<Talk_CallBackItem> call, Throwable t) {
                Log.e("에러다 짜샤", t.getMessage());
            }
        });
    }

    public void post() {

        String id = input_id.getText().toString();
        String pwd = input_pwd.getText().toString();
        String title = input_title.getText().toString();
        String content = input_content.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIservice retrofitService = retrofit.create(APIservice.class);
        Call<Talk_CallBackItem> call = retrofitService.writeTalk(id, pwd, title, content);

        call.enqueue(new Callback<Talk_CallBackItem>() {
            @Override
            public void onResponse(Call<Talk_CallBackItem> call, Response<Talk_CallBackItem> response) {
                Log.d("--------------성공!", response.body().getData().toString());
            }

            @Override
            public void onFailure(Call<Talk_CallBackItem> call, Throwable t) {
                Log.e("Not Response", t.getLocalizedMessage());
            }
        });
    }


    // 툴바 제어
    void initialize_toolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.icon_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        tv_title.setText("자유게시판");
    }

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


