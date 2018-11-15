package com.cover.technicalassessment.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

/* Extracted from Google Samples for Architecture Components
 https://github.com/googlesamples/android-architecture-components/blob/d7cd7a9414ef1eeba52a79ec76f6d9827cd50a36/GithubBrowserSample/app/src/androidTest/java/com/android/example/github/util/ViewModelUtil.kt
 */
public class ViewModelUtil {

    private ViewModelUtil() {}
    public static <T extends ViewModel> ViewModelProvider.Factory createFor(T model) {
        return new ViewModelProvider.Factory() {
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {
                if (modelClass.isAssignableFrom(model.getClass())) {
                    return (T) model;
                }
                throw new IllegalArgumentException("unexpected model class " + modelClass);
            }
        };
    }
}
