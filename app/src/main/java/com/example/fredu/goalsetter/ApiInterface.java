package com.example.fredu.goalsetter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    //https://goalsetapp.herokuapp.com/api/register?username=tino&email=tino@gmail.com&phone=08141894420&password=tino
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
        @Field("phone") String phone
    );
}
