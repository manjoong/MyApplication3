package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 마루소프트 on 2018-01-29.
 */
public interface APIservice {
    //public String I_URL = "http://cloud.marusoft.net/api/searchtime/";
    //public String I_URL = "http://searchapp.cafe24.com/searchtime/";
    //public String IMG_URL = I_URL + "image/";
    public static final String API_URL = "http://ec2-13-231-109-116.ap-northeast-1.compute.amazonaws.com/";







//
//        @GET("connect.php")
//        Call<Talk_CallBackItem> getTalk(
//                @Query("num") String num);
//
//
//    @FormUrlEncoded
//    @POST("insert.php")
//    Call<Talk_CallBackItem> writeTalk(@Field("num") Integer num, @Field("name") String name, @Field("password") String password, @Field("content") String content);




    @GET("connect_new.php") // 게시글 목혹
    Call<Talk_CallBackItem> getTalk();

    @GET("connect_reply.php")  //댓글 목록
    Call<Reply_CallBackItem> getReply(
            @Query("r_t_no") int r_t_no
    );


    @FormUrlEncoded //게시글
    @POST("insert_new.php") //insert_php에 대한 리턴값을 받는 post_cllback item.   밑에 변수들은 insert_new에서만 신경쓰면 됨
    Call<Post_CallBackItem> writeTalk(@Field("t_user_id") String t_user_id, @Field("t_pwd") String t_pwd,  @Field("t_title") String t_title, @Field("t_content") String t_content, @Field("t_like") int t_like);

    @FormUrlEncoded //좋아요 기능
    @POST("like_update.php") //insert_php에 대한 리턴값을 받는 post_cllback item.   밑에 변수들은 insert_new에서만 신경쓰면 됨
    Call<Post_CallBackItem> updateLike(@Field("t_no") int t_no, @Field("t_like") int t_like);

    @FormUrlEncoded //댓글 입력
    @POST("insert_reply.php")
    Call<Post_CallBackItem> writeReply(@Field("r_t_no") int r_t_no, @Field("r_user_id") String r_user_id,  @Field("r_content") String r_content);

    @FormUrlEncoded //게시글 삭제
    @POST("delete_talk.php") //insert_php에 대한 리턴값을 받는 post_cllback item.   밑에 변수들은 insert_new에서만 신경쓰면 됨
    Call<Post_CallBackItem> deleteTalk(@Field("t_no") int t_no);

//    @Multipart
//    @POST("insert.php")
//    Call<Talk_CallBackItem> writeTalk(
//            @Part("num") RequestBody num,
//            @Part("name") RequestBody name,
//            @Part("password") RequestBody password,
//            @Part("content") RequestBody content);
}
