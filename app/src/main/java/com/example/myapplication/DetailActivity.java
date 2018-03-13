package com.example.myapplication;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import util.DebugLog;

import static com.example.myapplication.APIservice.API_URL;

/**
 * Created by 마루소프트 on 2018-02-07.
 */

public class DetailActivity extends AppCompatActivity {
    TextView txt_title;
    TextView txt_content;
    TextView txt_date;
    TextView txt_id;
    TextView txt_like;
    TextView tv_title;
    ImageButton btn_r_write; //댓글 보내는 버튼
    ImageButton btn_like; //좋아요 버튼
    ImageButton btn_delete; //글 삭제 버튼

    int no;
    int like;
    String title;
    String content;
    String id;
    String pwd;
    String date;
    private String root_talk_no;
    private String string_like;
    private EditText input_r_id;
    private EditText input_r_content;
    RecyclerView recyclerView;
    ReplyTalkAdapter adapter;
    CommunityTalkAdapter adapter_talk_list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initialize();
        initialize_toolbar();

        input_r_id = (EditText) findViewById(R.id.input_r_id);
        input_r_content = (EditText) findViewById(R.id.input_r_content);
        View btn_write = (ImageButton) findViewById(R.id.btn_write); //게시글 쓰는 버튼 => 안보이게 할것
        btn_write.setVisibility(View.GONE);
        btn_r_write = (ImageButton) findViewById(R.id.btn_r_write); //댓글 쓰는 버튼
        btn_like = (ImageButton) findViewById(R.id.btn_like);
        btn_delete = (ImageButton) findViewById(R.id.btn_delete);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        //댓글버튼 클릭시! alert창
        btn_r_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(DetailActivity.this);
                alert_confirm.setMessage("댓글을 게시하겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("-------제발!!후후루루루-성공!", root_talk_no.toString()  );
                                post();
                                index();
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

        //글삭제 클릭시! alert창
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(DetailActivity.this);
                final EditText pwd = new EditText(DetailActivity.this);
                alert_confirm.setView(pwd);
                alert_confirm.setMessage("글을 삭제하시겠습니까? 비밀번호를 입력해 주세요.").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String value = pwd.getText().toString();

                                Log.d("-------제발!!후후루루루-성공!", value.toString()  );
                                delete();
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

        //좋아요 버튼 클릭시
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(DetailActivity.this);

                alert_confirm.setMessage("이 댓글을 좋아요 하시겠습니까?").setCancelable(false).setPositiveButton("확인",

                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("-------제발!!후후루루루-성공!", string_like.toString()  );

                                update_like();
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

        setRecyclerView();
        index();
    }

    public void initialize() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        TextView txt_id = (TextView) findViewById(R.id.txt_id);
        TextView txt_date = (TextView) findViewById(R.id.txt_date);
        TextView txt_title = (TextView) findViewById(R.id.txt_title);
        TextView txt_content = (TextView) findViewById(R.id.txt_content);
        TextView txt_like = (TextView) findViewById(R.id.txt_like);
        txt_title.setFocusable(false);
        txt_title.setClickable(false);
        txt_content.setFocusable(false);
        txt_content.setClickable(false);

        no=getIntent().getIntExtra("no",1);
        root_talk_no =  String.valueOf(no); //인트형의 글 번호를 스트링 형으로 변환한 것
        like=getIntent().getIntExtra("like",3);
        string_like = String.valueOf(like); //인트형의 좋아요 수를 스트링 형으로 변환한 것

        pwd = getIntent().getStringExtra("pwd");
        id = getIntent().getStringExtra("id");
        date = getIntent().getStringExtra("date");
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");

        txt_id.setText(id);
        txt_date.setText(date);
        txt_title.setText(title);
        txt_content.setText(content);
        txt_like.setText(string_like);

        Log.d("-------------제발!!-성공!", id.toString());
    }

    //좋아요 기능
    public  void update_like() {
        like += 1;
        final TextView txt_like = (TextView) findViewById(R.id.txt_like);
        string_like = String.valueOf(like);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        APIservice retrofitService = retrofit.create(APIservice.class);
        Call<Post_CallBackItem> call = retrofitService.updateLike(no, like);

        call.enqueue(new Callback<Post_CallBackItem>() {
            @Override
            public void onResponse(Call<Post_CallBackItem> call, Response<Post_CallBackItem> response) {
                Log.d("좋아요 기능 성공!", string_like.toString());
                txt_like.setText(string_like);
                setResult(RESULT_OK);
            }

            @Override
            public void onFailure(Call<Post_CallBackItem> call, Throwable t) {
                Log.e("좋아요 기능 실패ㅠㅠ", t.getMessage());
            }

        });
    }
    //댓글 써서 보내는 함수
    public void post() {
        final int r_t_no = no; //원례 글의 글번호
        final String r_user_id = input_r_id.getText().toString(); //닉네임을 읽어옴
        final String r_content = input_r_content.getText().toString(); // 내용을 읽어옴

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIservice retrofitService = retrofit.create(APIservice.class);
        Call<Post_CallBackItem> call = retrofitService.writeReply(r_t_no, r_user_id, r_content);

        call.enqueue(new Callback<Post_CallBackItem>() {
            @Override
            public void onResponse(Call<Post_CallBackItem> call, Response<Post_CallBackItem> response) {
                Log.d("-------------데이터 전송 성공", response.body().toString());
                Reply_CallBackItem.Data data = new Reply_CallBackItem.Data();
                data.setR_t_no(r_t_no);
                data.setR_user_id(r_user_id);
                data.setR_content(r_content);
                adapter.addItem(data);

                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Post_CallBackItem> call, Throwable t) {
                Log.e("왜 안되는건데...?", t.getMessage());
            }
        });

        input_r_content.setText(""); //글쓰고 나서 텍스트 창 초기화
    }

    //게시글 삭제하는 함수
    public void delete() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIservice retrofitService = retrofit.create(APIservice.class);
        Call<Post_CallBackItem> call = retrofitService.deleteTalk(no);
        call.enqueue(new Callback<Post_CallBackItem>() {
            @Override
            public void onResponse(Call<Post_CallBackItem> call, Response<Post_CallBackItem> response) {
                Log.d("------------글삭제 전송 성공", response.body().toString());
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFailure(Call<Post_CallBackItem> call, Throwable t) {
                Log.e("글삭제 왜 안되는건데...?", t.getMessage());
            }
        });

        input_r_content.setText(""); //글쓰고 나서 텍스트 창 초기화
    }

    void initialize_toolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.icon_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        tv_title.setText("글 보기");
    }

    //리사이클 뷰 설정
    void setRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //adapter = new CommunityTalkAdapter(getApplicationContext(), item);
        //recyclerView.setAdapter(adapter);
    }

    //댓글 목록 띄워 주는 함수
    public void index() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIservice retrofitService = retrofit.create(APIservice.class);
        Call<Reply_CallBackItem> call = retrofitService.getReply(no);
        call.enqueue(new Callback<Reply_CallBackItem>() {

            @Override
            public void onResponse(Call<Reply_CallBackItem> call, Response<Reply_CallBackItem> response) {
                DebugLog.v(response.body().getResults().toString());
                //Log.v("Debug : ", response.body().getData().toString());
                //이렇게 어댑터에 데이터를 보내주고

                adapter = new ReplyTalkAdapter(getApplicationContext(), response.body().getResults()); //appcompaty에선 context로 해줘야됨
//                textViewIndex.setText(response.body().getData().get(0).getName().toString());
                //adapter.notifyDataSetChanged();
                //어댑터를 연결시키면 끝이다.
                recyclerView.setAdapter(adapter);
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();

                    }
                });*/
                Log.e("데이터 읽어오기 성공", response.body().toString());
            }

            @Override
            public void onFailure(Call<Reply_CallBackItem> call, Throwable t) {
                Log.d("에러다 짜샤",t.getMessage());
            }
        });
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
