package com.cover.technicalassessment.address;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.cover.technicalassessment.R;
import com.cover.technicalassessment.carrier.CarrierFragment;
import com.cover.technicalassessment.common.AppComponent;
import com.cover.technicalassessment.common.BaseFragment;
import com.cover.technicalassessment.common.DaggerAppComponent;
import com.cover.technicalassessment.customviews.AutocompleteCustomAdapter;
import com.cover.technicalassessment.entities.Address;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressFragment extends BaseFragment<AddressViewModel> {

    private static final int THRESHOLD = 3;
    @BindView(R.id.auto_complete_address)
    AutoCompleteTextView autoCompleteTextView;

    public AddressFragment() {
        // Required empty public constructor
    }

    public static AddressFragment newInstance() {
        return new AddressFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        ButterKnife.bind(this, view);
        setupAutoComplete();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppComponent component = DaggerAppComponent.create();
        component.inject(this);
        viewModel.addresses.observe(this, addresses -> showDropdownList(addresses));
        viewModel.validate.observe(this, valid -> {
            if(valid) {
                navigate(CarrierFragment.newInstance(autoCompleteTextView.getText().toString()));
            } else {
                showError();
            }
        });
    }

    private void showDropdownList(List<Address> addresses) {
        AutocompleteCustomAdapter adapter = new AutocompleteCustomAdapter(getActivity(),
                R.layout.row_item,
                addresses);
        autoCompleteTextView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setupAutoComplete() {
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.setAddressInput(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
                String address = ((Address) parent.getItemAtPosition(position)).getDescription();
                autoCompleteTextView.setText(address);
            }
        );
    }


    @OnClick(R.id.next_btn)
    public void onNextButtonClicked() {
        viewModel.onNextButtonClicked();
    }

}
