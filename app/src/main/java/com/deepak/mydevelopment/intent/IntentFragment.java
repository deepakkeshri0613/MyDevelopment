package com.deepak.mydevelopment.intent;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepak.mydevelopment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntentFragment extends Fragment {




    public static IntentFragment createFragment()
    {
        return new IntentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_intent, container, false);
    }


}
