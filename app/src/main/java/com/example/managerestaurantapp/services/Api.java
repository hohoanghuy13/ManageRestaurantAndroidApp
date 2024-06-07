package com.example.managerestaurantapp.services;

import com.example.managerestaurantapp.models.UserModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    //Get data
    @GET("getaccount.php")
    Observable<UserModel> getAccount();

    @GET("getrole.php")
    Observable<UserModel> getRole(
            @Field("AccountName") String acc,
            @Field("Password") String pass
    );

    //------------------------------------------------------------

    //Post data
    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> dangKi(
            @Field("AccountName") String acc,
            @Field("Password") String pass
    );
    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangNhap(
            @Field("AccountName") String acc,
            @Field("Password") String pass
    );

    @POST("guilienket.php")
    @FormUrlEncoded
    Observable<UserModel> layLaiMK(
            @Field("email") String email
    );
}
