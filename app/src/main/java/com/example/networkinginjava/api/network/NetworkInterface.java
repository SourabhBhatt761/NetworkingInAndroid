package com.example.networkinginjava.api.network;

import com.example.networkinginjava.api.model.UsersResponse;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

//https://reqres.in/api/users?page=2

public interface NetworkInterface {

    /*@GET        -> to fetch the data from internet */
    /*@Mutltipart -> If we need to upload images or files, we need to send by using Multipart forms.
                        We will to mark the endpoint with @Multipart, and label at least one parameter with @Part.*/
    /*@Post       -> this is used to create new resources with the help of api in backend*/
    /*@Put        -> this is used to update/REPLACE the existing ENTITY */
    /*@Patch      -> this is used to update/REPLACE the existing data ITEMS individually.*/
    /*@Delete     -> this is used to delete the data */

    /*@Query -> after question mark data filled in url is set by annotation @Query*/
    /*@Path -> data which we input before question mark in url is set by this annotation */
    /*@Body -> payload for the POST call (serialized from a Java object to a JSON string)*/



    @GET("/api/users")
    Call<UsersResponse> getUsersListFromCallFn(@Query("page") int page);

    @GET("/api/users")
    UsersResponse getUsersListFromFn(@Query("page") int page);

    @GET("/api/users")
    Call<List<UsersResponse>> getUsersListFromList();

    @GET("/{api}/users")
    Call<UsersResponse> getUsersListWithParamAPI(@Path("api") String apiString, @Query("page") int page);

    @GET("/api/users")
    Response<UsersResponse> getUsersListFromResponseFn(@Query("page") int page);


    //just an example doesn't work with the above api !!!
    @POST("users/new")
    Call<UsersResponse> createUser(@Body UsersResponse user);

    @Multipart
    @POST("some/endpoint")
    Call<Response> uploadImage(@Part("description") String description, @Part("image") RequestBody image);

    public static void main(){

    }
}
