package com.cover.technicalassessment.address;

import android.arch.lifecycle.MutableLiveData;

import com.cover.technicalassessment.entities.Address;
import com.cover.technicalassessment.entities.CustomResponse;
import com.cover.technicalassessment.webservice.Webservice;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddressRepository {
    private final static String API_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/";
    private final static String ADDRESS_TYPE_TAG = "address";
    private final static String API_KEY = "AIzaSyBnMJjJXi3cyIVxzhdlYyaCG3PPQ4huF78";
    private Webservice webservice;
    private Retrofit retrofit;

    @Inject
    public AddressRepository() {
    }

    public MutableLiveData<List<Address>> getAddress(String address) {
        final MutableLiveData<List<Address>> data = new MutableLiveData<>();
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webservice = retrofit.create(Webservice.class);
        webservice.getAddress(address, ADDRESS_TYPE_TAG, API_KEY).enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                if(response.body() != null) {
                    data.postValue(response.body().predictions);
                }
            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {

            }
        });
        return data;
    }
}
