package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import util.DebugLog;

import static com.example.myapplication.APIservice.API_URL;

/**
 * Created by 마루소프트 on 2018-02-02.
 */

public class TalkListActivity extends AppCompatActivity {
    TextView textViewIndex;
    TextView tv_title;
    RecyclerView recyclerView;
    CommunityTalkAdapter adapter;
    ImageButton btn_write;

    Talk_CallBackItem item;

    //설명 안해도 알지?
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.talk_list);
        btn_write = (ImageButton) findViewById(R.id.btn_write);
        View btn_delete = (ImageButton) findViewById(R.id.btn_delete); //글 삭제 버튼  없애기
        btn_delete.setVisibility(View.GONE);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        textViewIndex = (TextView) findViewById(R.id.txtRetrofitTest);
        tv_title = (TextView) findViewById(R.id.tv_title);
        setRecyclerView();
        initialize_toolbar();
        index();

        //글쓰기 버튼 클릭시
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WriteTalkActivity.class);

                //액티비티 시작!
                startActivityForResult(intent, 0);

            }
        });

    }



    //리사이클 뷰 설정
    void setRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //adapter = new CommunityTalkAdapter(getApplicationContext(), item);
        //recyclerView.setAdapter(adapter);
    }







    //base url설정, 파싱 성공시 => 어댑터와 연결 실패시 => 처리
    public void index() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIservice retrofitService = retrofit.create(APIservice.class);
        Call<Talk_CallBackItem> call = retrofitService.getTalk();
        call.enqueue(new Callback<Talk_CallBackItem>() {

            @Override
            public void onResponse(Call<Talk_CallBackItem> call, Response<Talk_CallBackItem> response) {
                DebugLog.v(response.body().getResults().toString());
                //Log.v("Debug : ", response.body().getData().toString());
                //이렇게 어댑터에 데이터를 보내주고
                adapter = new CommunityTalkAdapter(TalkListActivity.this, response.body().getResults()); //appcompaty에선 context로 해줘야됨
//                textViewIndex.setText(response.body().getData().get(0).getName().toString());
                //어댑터를 연결시키면 끝이다.
                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Talk_CallBackItem> call, Throwable t) {
                Log.d("에러다 짜샤왜~~",t.getMessage());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 || requestCode == 1002) {
            if(resultCode == RESULT_OK){
                index();
            }
        }
    }//onActivityResult
}
