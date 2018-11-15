package com.cover.technicalassessment.carrier;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CarrierViewModelTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    private CarrierRepository repository = Mockito.mock(CarrierRepository.class);

    @Mock
    private Context context = Mockito.mock(Context.class);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void setAddressInput_empty() {
        String carrier = "";
        CarrierViewModel viewModel = new CarrierViewModel(repository);

        viewModel.setCarrierInput(carrier, context);

        Assert.assertEquals("", viewModel.carrierInput.getValue());
    }

    @Test
    public void setAddressInput_carrierLessThan3() {
        String carrier = "Zu";
        CarrierViewModel viewModel = new CarrierViewModel(repository);

        viewModel.setCarrierInput(carrier, context);
        viewModel.onNextButtonClicked();

        Assert.assertFalse(viewModel.validate.getValue());
    }

    @Test
    public void setAddressInput_carrierMoreThan3_matchTrue() {
        String carrier = "Zurich";
        CarrierViewModel viewModel = Mockito.spy(new CarrierViewModel(repository));

        MutableLiveData<Boolean> isMatch = new MutableLiveData<>();
        isMatch.setValue(true);

        Mockito.when(viewModel.isMatch()).thenReturn(isMatch);


        Mockito.doReturn(isMatch).when(viewModel).isMatch();
        viewModel.setCarrierInput(carrier, context);
        viewModel.onNextButtonClicked();

        Assert.assertTrue(viewModel.validate.getValue());
    }

    @Test
    public void setAddressInput_carrierMoreThan3_matchFalse() {
        String carrier = "Zuri";
        CarrierViewModel viewModel = Mockito.spy(new CarrierViewModel(repository));

        MutableLiveData<Boolean> isMatch = new MutableLiveData<>();
        isMatch.setValue(false);

        Mockito.when(viewModel.isMatch()).thenReturn(isMatch);


        Mockito.doReturn(isMatch).when(viewModel).isMatch();
        viewModel.setCarrierInput(carrier, context);
        viewModel.onNextButtonClicked();

        Assert.assertFalse(viewModel.validate.getValue());
    }

    @Test
    public void shouldInvoke_getCarrier() {
        String carrier = "Zuri";

        repository.getCarrier(carrier, context);

        Mockito.verify(repository).getCarrier(carrier, context);
    }
}
