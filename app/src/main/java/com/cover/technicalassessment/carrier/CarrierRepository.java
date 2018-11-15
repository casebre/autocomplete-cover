package com.cover.technicalassessment.carrier;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cover.technicalassessment.utils.StringValidationUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class CarrierRepository {

    private final static String INSURERS_JSON_FILE = "carrier.json";
    private final static String MEMBER_NAME_TAG = "insurance_carriers";

    @Inject
    public CarrierRepository() {
    }

    public MutableLiveData<List<String>> getCarrier(String carrier, Context context) {
        final MutableLiveData<List<String>> data = new MutableLiveData<>();
        String json = loadFromFile(context, INSURERS_JSON_FILE);
        JsonParser parser = new JsonParser();
        List<String> insurers = getArrayListFromJson(parser.parse(json).getAsJsonObject().getAsJsonArray(MEMBER_NAME_TAG));

        List<String> filtered = StringValidationUtil.filterStringList(insurers, carrier);

        /*List<String> filtered = insurers
                .stream()
                .filter(s -> s.toLowerCase().contains(carrier))
                .collect(Collectors.toList());
                */

        data.setValue(filtered);

        return data;
    }

    public MutableLiveData<Boolean> matchCarrier(String carrier, Context context) {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();
        String json = loadFromFile(context, INSURERS_JSON_FILE);
        JsonParser parser = new JsonParser();
        List<String> insurers = getArrayListFromJson(parser.parse(json).getAsJsonObject().getAsJsonArray(MEMBER_NAME_TAG));

        Boolean isPresent = StringValidationUtil.matchesAny(insurers, carrier);
        /*Boolean isPresent = insurers.stream()
                .filter(s -> s.equalsIgnoreCase(carrier))
                .findAny()
                .isPresent();
                */

        data.setValue(isPresent);

        return data;
    }


    private String loadFromFile(@NonNull Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    private List<String> getArrayListFromJson(JsonArray jsonArray) {
        Type collectionType = new TypeToken<List<String>>() {}.getType();
        Gson gson = new Gson();
        return gson.fromJson(jsonArray, collectionType);
    }



}
