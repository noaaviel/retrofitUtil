package com.example.retrofittutorial;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface classAPI {

    @GET("/api/unknown")
    Call<MultipleResource> doGetListResources();

    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);

    /*
    *
    *   @Body – Sends Java objects as request body.

        @Field – send data as form-urlencoded.
        *  This requires a @FormUrlEncoded annotation attached with the method.
        * The @Field parameter works only with a POST. @Field requires a mandatory parameter.
        *  In cases when @Field is optional, we can use @Query instead and pass a null value.
    *
    *
    * */


}
