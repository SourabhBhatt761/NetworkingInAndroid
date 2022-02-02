package com.example.networkinginjava;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.networkinginjava.api.network.NetworkInterface;
import com.example.networkinginjava.api.network.RetrofitClient;
import com.example.networkinginjava.api.model.UsersResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    NetworkInterface myApi =  RetrofitClient.getInstance().getMyApi();

    //for every api call create
    private final MutableLiveData<UsersResponse> usersResponseMutableLiveData = new MutableLiveData<UsersResponse>();
    private final MutableLiveData<UsersResponse> random = new MutableLiveData<UsersResponse>();
    private final MutableLiveData<UsersResponse> random2 = new MutableLiveData<UsersResponse>();

    private void getUsersListFromApi(int page) {

         Call<UsersResponse> response = myApi.getUsersListFromCallFn(page);
         response.enqueue(new Callback<UsersResponse>() {
             @Override
             public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                 if (response.isSuccessful()) {
                        usersResponseMutableLiveData.setValue(response.body());         //setting the value in mutable live data
                 }
             }
             @Override
             public void onFailure(Call<UsersResponse> call, Throwable t) {
                 Log.i("uni",t.getMessage());
             }
         });

    }

    private void getUsersListpi(int page) {

        Call<UsersResponse> response = myApi.getUsersListFromCallFn(2);
        response.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.isSuccessful()) {
                    random.setValue(response.body());         //setting the value in mutable live data
                }
            }
            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Log.i("uni",t.getMessage());

            }
        });

    }

    public LiveData<UsersResponse> getUsersList(int page){
        getUsersListFromApi(page);

        return usersResponseMutableLiveData;
    }

}
