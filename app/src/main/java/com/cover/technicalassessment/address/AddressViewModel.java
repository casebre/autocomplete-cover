package com.cover.technicalassessment.address;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.cover.technicalassessment.common.SingleLiveEvent;
import com.cover.technicalassessment.entities.Address;
import com.cover.technicalassessment.utils.StringValidationUtil;

import java.util.List;

import javax.inject.Inject;

public class AddressViewModel extends ViewModel {

    private final static int MIN_ADDRESS_LENGTH = 8;
    private AddressRepository repository;
    public MutableLiveData<String> addressInput = new MutableLiveData<>();
    public SingleLiveEvent<Boolean> validate = new SingleLiveEvent<>();

    @Inject
    public AddressViewModel(AddressRepository addressRepository) {
        this.repository = addressRepository;
    }

    public final LiveData<List<Address>> addresses = Transformations.switchMap(addressInput,
            new Function<String, LiveData<List<Address>>>() {
                @Override
                public LiveData<List<Address>> apply(String address) {
                    return repository.getAddress(address);
                }
            });

    public void setAddressInput(String input) {
        addressInput.setValue(input);
    }

    public void onNextButtonClicked() {
        if(StringValidationUtil.hasMinimumRequired(addressInput.getValue(), MIN_ADDRESS_LENGTH)) {
            validate.setValue(true);
        } else {
            validate.setValue(false);
        }
    }
}
