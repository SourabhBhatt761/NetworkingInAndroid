package com.example.networkinginjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.networkinginjava.api.MainViewModel;
import com.example.networkinginjava.api.empty.RetrofitClient;
import com.example.networkinginjava.api.model.DataItem;
import com.example.networkinginjava.api.model.UsersResponse;
import com.example.networkinginjava.databinding.ActivityMainBinding;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding ;
    private RetrofitClient retrofitClient = RetrofitClient.getInstance();
    public MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        callBackMethod();
//        responseMethod();

//        uploadImageByApiCall();

    }

    private void uploadImageByApiCall() {

        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        File file = new File("/storage/emulated/0/Pictures/MyApp/test.png");
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_PNG, file);

        Call<Response> call = retrofitClient.getMyApi().uploadImage("test", requestBody);
    }

    private void responseMethod() {

        Response<UsersResponse> r = RetrofitClient.getInstance().getMyApi().getUsersListFromResponseFn(2);

//        if(r.isSuccessful()){
//            UsersResponse usersResponse  = new UsersResponse();
//                    Log.i("uni", "users response " + r.body().getData());
//
//                    for(DataItem items: r.body().getData()){
//                        Log.i("api","items data "+ items.getFirstName());
//                        Log.i("api","items data "+ items.getEmail());
//                        Log.i("api","items data "+ items.getLastName());
//                        Log.i("api","items data "+ items.getId());
//                    }
//        }
    }


    //    Call back method
    private void callBackMethod() {


        mainViewModel.getUsersList(10).observe(this,item->{

        });


        mainViewModel.getUsersList(2).observe(this,item ->{
           Log.i("uni","page "+ item.getPage());
           Log.i("uni","item.getSupport().getText() "+item.getSupport().getText());
           Log.i("uni","item.getSupport().getUrl() "+item.getSupport().getUrl());
           Log.i("uni","item.getTotal() "+item.getTotal());
           

           for (DataItem dataItem : item.getData()){
               Log.i("uni","dataItem.getId() "+ dataItem.getId());
               Log.i("uni","dataItem.getLastName() "+ dataItem.getLastName());
               Log.i("uni","dataItem.getEmail() "+ dataItem.getEmail());

           }

        });



    }
}