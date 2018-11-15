package com.cover.technicalassessment.common;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.cover.technicalassessment.R;
import com.cover.technicalassessment.main.MainActivity;
import com.cover.technicalassessment.utils.ViewModelUtil;

import javax.inject.Inject;

public abstract class BaseFragment<T extends ViewModel> extends Fragment {

    @Inject
    protected T viewModel;

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ViewModelProvider.Factory viewModelFactory = ViewModelUtil.createFor(viewModel);
        ViewModelProviders.of(this, viewModelFactory).get(viewModel.getClass());
    }

    public void navigate(Fragment fragment) {
        ((MainActivity) getActivity()).nextStep(fragment);
    }

    public void showError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.common_no_options_title)
                .setMessage(R.string.common_no_options_message)
                .setPositiveButton(R.string.common_ok,
                        (dialogInterface, i) -> dialogInterface.dismiss());
        builder.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
