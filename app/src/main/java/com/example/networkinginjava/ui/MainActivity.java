package com.example.networkinginjava.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.networkinginjava.MainViewModel;
import com.example.networkinginjava.api.network.RetrofitClient;
import com.example.networkinginjava.api.model.DataItem;
import com.example.networkinginjava.api.model.UsersResponse;
import com.example.networkinginjava.databinding.ActivityMainBinding;
import com.example.networkinginjava.ui.adapters.MyRecyclerViewAdapter;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RetrofitClient retrofitClient = RetrofitClient.getInstance();
    public MainViewModel mainViewModel;
    MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setUpRecyclerView();
        //what we're using here to fetch data
        callBackMethod();
//        uploadImageByApiCall();

    }

    private void setUpRecyclerView() {

        binding.recyclerView.setVisibility(View.GONE);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter();
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerView.setAdapter(myRecyclerViewAdapter);

    }

    //Call back method
    private void callBackMethod() {

        mainViewModel.getUsersList(2).observe(this, item -> {
//            Log.i("uni", "page " + item.getPage());
//            Log.i("uni", "item.getSupport().getText() " + item.getSupport().getText());
//            Log.i("uni", "item.getSupport().getUrl() " + item.getSupport().getUrl());
//            Log.i("uni", "item.getTotal() " + item.getTotal());
//
//
//            for (DataItem dataItem : item.getData()) {
//                Log.i("uni", "dataItem.getId() " + dataItem.getId());
//                Log.i("uni", "dataItem.getLastName() " + dataItem.getLastName());
//                Log.i("uni", "dataItem.getEmail() " + dataItem.getEmail());
//
//            }

            binding.textview1.setText(String.valueOf(item.getPage()));   //return type is int and text view only displays string values !
            binding.textview2.setText("per page : " + item.getPerPage());
            binding.textview3.setText("total : " + item.getTotal());
            binding.textview4.setText("total pages : " + item.getTotalPages());
            binding.textview5.setText("support text : " + item.getSupport().getText());
            binding.textview6.setText("support url : " + item.getSupport().getUrl());

            showDataOnPhone(item.getData());
        });

    }

    private void showDataOnPhone(List<DataItem> data) {
        Log.i("list", "data " + data.size());
        if (data.size() > 0) {
            binding.recyclerView.setVisibility(View.VISIBLE);
            myRecyclerViewAdapter.updateAdapterList(data);
        }

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
}