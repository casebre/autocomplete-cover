package com.cover.technicalassessment.carrier;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.cover.technicalassessment.common.SingleLiveEvent;
import com.cover.technicalassessment.utils.StringValidationUtil;

import java.util.List;

import javax.inject.Inject;

public class CarrierViewModel extends ViewModel {

    private final static int MIN_ADDRESS_LENGTH = 3;

    private CarrierRepository repository;
    public MutableLiveData<String> carrierInput = new MutableLiveData<>();
    private Context context;
    public SingleLiveEvent<Boolean> validate = new SingleLiveEvent<>();

    @Inject
    public CarrierViewModel(CarrierRepository carrierRepository) {
        this.repository = carrierRepository;
    }

    public final LiveData<List<String>> insurers = Transformations.switchMap(carrierInput,
            new Function<String, LiveData<List<String>>>() {
                @Override
                public LiveData<List<String>> apply(String name) {
                    return repository.getCarrier(name, context);
                }
            });

    public void setCarrierInput(String input, Context context) {
        this.context = context;
        carrierInput.setValue(input);
    }

    public void onNextButtonClicked() {
        if(StringValidationUtil.hasMinimumRequired(carrierInput.getValue(), MIN_ADDRESS_LENGTH)) {
            if (isMatch().getValue()) {
                validate.setValue(true);
            } else {
                validate.setValue(false);
            }
        } else {
            validate.setValue(false);
        }
    }

    @VisibleForTesting
    LiveData<Boolean> isMatch() {
        return repository.matchCarrier(carrierInput.getValue(), context);
    }
}
