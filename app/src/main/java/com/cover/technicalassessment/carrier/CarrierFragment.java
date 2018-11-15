package com.cover.technicalassessment.carrier;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.cover.technicalassessment.R;
import com.cover.technicalassessment.common.AppComponent;
import com.cover.technicalassessment.common.BaseFragment;
import com.cover.technicalassessment.common.DaggerAppComponent;
import com.cover.technicalassessment.customviews.AutocompleteInsurerAdapter;
import com.cover.technicalassessment.summary.SummaryFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarrierFragment extends BaseFragment<CarrierViewModel> {

    private final static String ARG_ADDRESS = "ADDRESS";
    private static final int THRESHOLD = 3;

    @BindView(R.id.auto_complete_carrier)
    AutoCompleteTextView autoCompleteTextView;

    private String address;

    public CarrierFragment() {
        // Required empty public constructor
    }

    public static CarrierFragment newInstance(String param1) {
        CarrierFragment carrierFragment = new CarrierFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ADDRESS, param1);
        carrierFragment.setArguments(args);
        return carrierFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            address = getArguments().getString(ARG_ADDRESS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carrier, container, false);
        ButterKnife.bind(this, view);
        setupAutoCompleteTextView();
        return view;
    }

    private void setupAutoCompleteTextView() {
        autoCompleteTextView.setThreshold(THRESHOLD);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {
                if(count >= THRESHOLD) {
                    viewModel.setCarrierInput(charSequence.toString(), getContext());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppComponent component = DaggerAppComponent.create();
        component.inject(this);
        viewModel.insurers.observe(this, insurers ->  showDropdownList(insurers));
        viewModel.validate.observe(this, valid -> {
            if(valid) {
                navigate(SummaryFragment.newInstance(address, autoCompleteTextView.getText().toString()));
            } else {
                showError();
            }
        });
    }

    public void showDropdownList(List<String> insurers) {
        if(insurers != null) {
            AutocompleteInsurerAdapter adapter = new AutocompleteInsurerAdapter(getActivity(),
                    R.layout.row_item,
                    insurers);
            autoCompleteTextView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.next_btn)
    public void onNextButtonClicked() {
        viewModel.onNextButtonClicked();
    }

}