package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    TextView textViewIndex;
    private EditText input_id;
    private EditText input_pwd;
    private EditText input_title;
    private EditText input_content;
    Button input_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communuty);
        textViewIndex = (TextView) findViewById(R.id.txtRetrofitTest);
        input_id = findViewById(R.id.input_id);
        input_pwd = findViewById(R.id.input_pwd);
        input_title = findViewById(R.id.input_title);
        input_content = findViewById(R.id.input_content);
        input_button = findViewById(R.id.input_button);



        input_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                post();

            }
        });

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