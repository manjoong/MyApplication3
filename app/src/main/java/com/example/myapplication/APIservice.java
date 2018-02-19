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
        @GET("connect.php")
        Call<Talk_CallBackItem> getTalk(
                @Query("num") String num);


    @FormUrlEncoded
    @POST("insert.php")
    Call<Talk_CallBackItem> writeTalk(@Field("num") Integer num, @Field("name") String name, @Field("password") String password, @Field("content") String content);




//    @GET("connect_new.php")
//    Call<Talk_CallBackItem_new> getTalk(
//            @Query("t_num") String t_num);
//
//
//    @FormUrlEncoded
//    @POST("insert_new.php")
//    Call<Talk_CallBackItem_new> writeTalk(@Field("t_user_id") String t_user_id, @Field("t_pwd") String t_pwd, @Field("t_content") String t_content, @Field("t_title") String t_title);

//    @Multipart
//    @POST("insert.php")
//    Call<Talk_CallBackItem> writeTalk(
//            @Part("num") RequestBody num,
//            @Part("name") RequestBody name,
//            @Part("password") RequestBody password,
//            @Part("content") RequestBody content);
}
