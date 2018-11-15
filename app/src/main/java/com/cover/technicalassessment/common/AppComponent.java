package com.cover.technicalassessment.common;

import com.cover.technicalassessment.address.AddressFragment;
import com.cover.technicalassessment.address.AddressRepository;
import com.cover.technicalassessment.carrier.CarrierFragment;
import com.cover.technicalassessment.carrier.CarrierRepository;

import dagger.Component;

@Component
public interface AppComponent {

    AddressRepository getAddressRepository();
    CarrierRepository getCarrierRepository();

    void inject(AddressFragment fragment);
    void inject(CarrierFragment fragment);

}
