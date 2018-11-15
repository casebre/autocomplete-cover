package com.cover.technicalassessment.summary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cover.technicalassessment.R;
import com.cover.technicalassessment.main.MainActivity;
import com.cover.technicalassessment.main.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SummaryFragment extends Fragment {

    private static final String ARG_ADDRESS = "ADDRESS";
    private static final String ARG_INSURER = "INSURER";

    private String address;
    private String insurer;

    @BindView(R.id.text_view_address)
    TextView addressTv;

    @BindView(R.id.text_view_insurer)
    TextView insurerTv;

    public SummaryFragment() {
        // Required empty public constructor
    }

    public static SummaryFragment newInstance(String param1, String param2) {
        SummaryFragment fragment = new SummaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ADDRESS, param1);
        args.putString(ARG_INSURER, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            address = getArguments().getString(ARG_ADDRESS);
            insurer = getArguments().getString(ARG_INSURER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        ButterKnife.bind(this, view);
        addressTv.setText(address);
        insurerTv.setText(insurer);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.button_finish)
    public void onFinishButtonClicked() {
        ((MainActivity)getActivity()).nextStep(MainFragment.newInstance());
    }
}
