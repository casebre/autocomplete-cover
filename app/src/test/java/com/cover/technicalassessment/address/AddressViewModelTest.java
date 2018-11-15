package com.cover.technicalassessment.address;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AddressViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    private AddressRepository repository = Mockito.mock(AddressRepository.class);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void buttonNextClicked_emptyAddress() {
//
//
//        //Mockito.when(repository.getAddress("")).thenReturn(new MutableLiveData<>());
//        //viewModel = Mockito.spy(new AddressViewModel(repository));
//        //validate = Mockito.spy(new SingleLiveEvent<>());
//        //addressInput = Mockito.spy(new MutableLiveData<>());
//        //Mockito.when(addressInput.getValue()).thenReturn("");
//
//        // Trigger
//        //viewModel.onNextButtonClicked();
//
//        // Validation
//        //Mockito.verify(repository, Mockito.never()).getAddress(address);
//        //Mockito.verify(viewModel).validate.setValue(false);
//        //Mockito.verify(spyAddressViewModel).setAddressInput("");
//
//    }

//    @Test
//    public void setAddressInput_value() {
//        String address = "";
//
//        Mockito.doAnswer(invocation -> {
//            Object[] arguments = invocation.getArguments();
//            if (arguments != null && arguments.length > 0 && arguments[0] != null) {
//
//                String addressUserInput = (String) arguments[0];
//                addressInput.setValue(addressUserInput);
//            }
//            return null;
//        }).when(viewModel).setAddressInput(Mockito.anyString());
//
//        viewModel.setAddressInput(address);
//
//        assertThat(addressInput.getValue(), equalTo(""));
//    }

    @Test
    public void setAddressInput_empty() {
        String address = "";
        AddressViewModel viewModel = new AddressViewModel(repository);

        viewModel.setAddressInput(address);

        Assert.assertEquals("", viewModel.addressInput.getValue());
    }

    @Test
    public void setAddressInput_string() {
        String address = "123 Main Street";
        AddressViewModel viewModel = new AddressViewModel(repository);

        viewModel.setAddressInput(address);

        Assert.assertEquals("123 Main Street", viewModel.addressInput.getValue());
    }

    @Test
    public void onButtonClicked_emptyString_shouldNotValidate() {
        String address = "";
        AddressViewModel viewModel = new AddressViewModel(repository);

        viewModel.setAddressInput(address);
        viewModel.onNextButtonClicked();

        Assert.assertFalse(viewModel.validate.getValue());
    }

    @Test
    public void onButtonClicked_addressLessThan8_shouldNotValidate() {
        String address = "123 Mai";
        AddressViewModel viewModel = new AddressViewModel(repository);

        viewModel.setAddressInput(address);
        viewModel.onNextButtonClicked();

        Assert.assertFalse(viewModel.validate.getValue());
    }

    @Test
    public void onButtonClicked_addressLessThan8_shouldValidate() {
        String address = "123 Main Street";
        AddressViewModel viewModel = new AddressViewModel(repository);

        viewModel.setAddressInput(address);
        viewModel.onNextButtonClicked();

        Assert.assertTrue(viewModel.validate.getValue());
    }

    @Test
    public void shouldRetrieveAddressList() {
        String address = "123 Main Street";

        Mockito.when(repository.getAddress(Mockito.anyString())).thenReturn(new MutableLiveData<>());

        repository.getAddress(address);

        Mockito.verify(repository).getAddress(address);
    }
}
